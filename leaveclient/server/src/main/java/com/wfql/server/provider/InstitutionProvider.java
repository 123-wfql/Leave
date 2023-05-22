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

import com.wfql.server.dao.InstitutionDao;
import com.wfql.server.database.LeaveSysDB;
import com.wfql.server.entity.Institution;

public class InstitutionProvider extends ContentProvider {

    private LeaveSysDB leaveSysDB;
    private InstitutionDao institutionDao;

    private static final int INSTITUTION_TABLE = 1;
    private static final int INSTITUTION_TABLE_ROW = 2;
    private static final int INSTITUTION_SQL = 3;
    private static final int INSTITUTION_SQL_ROW = 4;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.wfql.server.provider.InstitutionProvider", "Institution/all", INSTITUTION_TABLE);
        uriMatcher.addURI("com.wfql.server.provider.InstitutionProvider", "Institution/all/#", INSTITUTION_TABLE_ROW);
        uriMatcher.addURI("com.wfql.server.provider.InstitutionProvider", "Institution/sql", INSTITUTION_SQL);
        uriMatcher.addURI("com.wfql.server.provider.InstitutionProvider", "Institution/sql/#", INSTITUTION_SQL_ROW);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        leaveSysDB = Room.databaseBuilder(context, LeaveSysDB.class, "LeaveSysDB")
                .allowMainThreadQueries()
                .addMigrations()
                .build();
        institutionDao = leaveSysDB.institutionDao();
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case INSTITUTION_TABLE:
                Institution institution = new Institution();
                // TODO:parse
                institutionDao.insert(institution);
                break;
            case INSTITUTION_TABLE_ROW:

                break;
            case INSTITUTION_SQL:

                break;
            case INSTITUTION_SQL_ROW:

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
            case INSTITUTION_TABLE:
                cursor = institutionDao.queryAll();
                break;
            case INSTITUTION_TABLE_ROW:

            case INSTITUTION_SQL:
                cursor = null;
                break;

            case INSTITUTION_SQL_ROW:
                String baseQuery = "SELECT %s FROM Insti%s%s";
                String projectionClause = (projection != null && projection.length > 0) ? TextUtils.join(",", projection) : "*";
                String selectionClause = (!TextUtils.isEmpty(selection)) ? " WHERE " + selection : "";
                String sortOrderClause = (!TextUtils.isEmpty(sortOrder)) ? " ORDER BY " + sortOrder : "";
                String queryString = String.format(baseQuery, projectionClause, selectionClause, sortOrderClause);
                Log.d("query", "生成了SQL语句："+queryString);
                SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString, selectionArgs);
                cursor = institutionDao.queryByExecSql(query);
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