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

import com.wfql.server.dao.UserDao;
import com.wfql.server.database.LeaveSysDB;
import com.wfql.server.entity.User;

public class UserProvider extends ContentProvider {

    private LeaveSysDB leaveSysDB;
    private UserDao userDao;



    private static final int USER_TABLE = 1;
    private static final int USER_TABLE_ROW = 2;
    private static final int USER_SQL = 3;
    private static final int USER_SQL_ROW = 4;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);



    static {
        uriMatcher.addURI("com.wfql.server.provider.UserProvider", "User/all", USER_TABLE);
        uriMatcher.addURI("com.wfql.server.provider.UserProvider", "User/all/#", USER_TABLE_ROW);
        uriMatcher.addURI("com.wfql.server.provider.UserProvider", "User/sql", USER_SQL);
        uriMatcher.addURI("com.wfql.server.provider.UserProvider", "User/sql/#", USER_SQL_ROW);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        leaveSysDB = Room.databaseBuilder(context, LeaveSysDB.class, "LeaveSysDB")
                .allowMainThreadQueries()
                .addMigrations()
                .build();
        userDao = leaveSysDB.userDao();
        Log.d("wfql", "userDao in provider onCreate");
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case USER_TABLE:
                User user = new User();
                user.setUserId(values.getAsString("userId"));
                user.setUserPwd(values.getAsString("userPwd"));
                user.setUserName(values.getAsString("userName"));
                user.setVerifyPhone(values.getAsString("verifyPhone"));
                user.setUserGender(values.getAsString("userGender"));
                user.setEnterYear(values.getAsInteger("enterYear"));
                user.setIconUrl(values.getAsString("iconUrl"));
                user.setCheckerLv(values.getAsInteger("checkLv"));
                user.setUserRemark(values.getAsString("userRemark"));
                user.setDepartment(values.getAsString("department"));
                user.setGovernLv(values.getAsInteger("governLv"));
                user.setJob(values.getAsString("job"));
                userDao.insert(user);
                break;
            case USER_TABLE_ROW:

                break;
            case USER_SQL:

                break;
            case USER_SQL_ROW:

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
            case USER_TABLE:
                // 查询整个 Login 表
                cursor = userDao.queryAll();
                break;
            case USER_TABLE_ROW:

            case USER_SQL_ROW:
                // 查询指定 ID 的 Login
                cursor = null;
                break;

            case USER_SQL:
                String baseQuery = "SELECT %s FROM User%s%s";
                String projectionClause = (projection != null && projection.length > 0) ? TextUtils.join(",", projection) : "*";
                String selectionClause = (!TextUtils.isEmpty(selection)) ? " WHERE " + selection : "";
                String sortOrderClause = (!TextUtils.isEmpty(sortOrder)) ? " ORDER BY " + sortOrder : "";
                String queryString = String.format(baseQuery, projectionClause, selectionClause, sortOrderClause);
                Log.d("query", "生成了SQL语句："+queryString);
                SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString, selectionArgs);
                cursor = userDao.queryByExecSql(query);
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