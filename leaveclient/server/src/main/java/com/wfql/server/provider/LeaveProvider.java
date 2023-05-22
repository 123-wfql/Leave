package com.wfql.server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.room.Room;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.wfql.server.dao.LeaveDao;
import com.wfql.server.database.LeaveSysDB;
import com.wfql.server.entity.Leave;

public class LeaveProvider extends ContentProvider {

    private LeaveSysDB leaveSysDB;
    private LeaveDao leaveDao;

    private static final int LEAVE_TABLE = 1;
    private static final int LEAVE_TABLE_ROW = 2;
    private static final int LEAVE_SQL = 3;
    private static final int LEAVE_SQL_ROW = 4;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.wfql.server.provider.LeaveProvider", "Leave/all", LEAVE_TABLE);
        uriMatcher.addURI("com.wfql.server.provider.LeaveProvider", "Leave/all/#", LEAVE_TABLE_ROW);
        uriMatcher.addURI("com.wfql.server.provider.LeaveProvider", "Leave/sql", LEAVE_SQL);
        uriMatcher.addURI("com.wfql.server.provider.LeaveProvider", "Leave/sql/#", LEAVE_SQL_ROW);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        leaveSysDB = Room.databaseBuilder(context, LeaveSysDB.class, "LeaveSysDB")
                .allowMainThreadQueries()
                .addMigrations()
                .build();
        leaveDao = leaveSysDB.leaveDao();
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case LEAVE_TABLE:
                Leave leave = new Leave();
                // TODO:parse
                leaveDao.insert(leave);
                break;
            case LEAVE_TABLE_ROW:

                break;
            case LEAVE_SQL:

                break;
            case LEAVE_SQL_ROW:

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return uri;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int match = uriMatcher.match(uri);
        Cursor cursor;

        switch (match) {
            case LEAVE_TABLE:
                cursor = leaveDao.queryAll();
                break;
            case LEAVE_TABLE_ROW:

            case LEAVE_SQL_ROW:
                cursor = null;
                break;

            case LEAVE_SQL:
                String baseQuery = "SELECT %s FROM Insti%s%s";
                String projectionClause = (projection != null && projection.length > 0) ? TextUtils.join(",", projection) : "*";
                String selectionClause = (!TextUtils.isEmpty(selection)) ? " WHERE " + selection : "";
                String sortOrderClause = (!TextUtils.isEmpty(sortOrder)) ? " ORDER BY " + sortOrder : "";
                String queryString = String.format(baseQuery, projectionClause, selectionClause, sortOrderClause);
                Log.d("query", "生成了SQL语句："+queryString);
                SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString, selectionArgs);
                cursor = leaveDao.queryByExecSql(query);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data at the given URI.

        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}