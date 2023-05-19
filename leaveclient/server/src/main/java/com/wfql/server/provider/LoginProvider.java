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

import com.wfql.server.dao.LoginDao;
import com.wfql.server.database.LeaveSysDB;
import com.wfql.server.entity.Login;

public class LoginProvider extends ContentProvider {

    private LeaveSysDB leaveSysDB;
    private LoginDao loginDao;
    // 创建 UriMatcher 对象
    private static final int LOGIN_TABLE = 1;
    private static final int LOGIN_TABLE_ROW = 2;
    private static final int LOGIN_SQL = 3;
    private static final int LOGIN_SQL_ROW = 4;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);



    static {
        uriMatcher.addURI("com.wfql.server.provider.LoginProvider", "Login/all", LOGIN_TABLE);
        uriMatcher.addURI("com.wfql.server.provider.LoginProvider", "Login/all/#", LOGIN_TABLE_ROW);
        uriMatcher.addURI("com.wfql.server.provider.LoginProvider", "Login/sql", LOGIN_SQL);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        leaveSysDB = Room.databaseBuilder(context, LeaveSysDB.class, "LeaveSysDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations()
                .build();
        loginDao = leaveSysDB.loginDao();
        Log.d("create", "loginDao in provider onCreate");
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Login login = new Login();
        login.setLoginId(values.getAsString("loginId"));
        login.setLoginPwd(values.getAsString("loginPwd"));
        login.setRemember(values.getAsBoolean("remember"));
        loginDao.insert(login);
        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int match = uriMatcher.match(uri);
        Cursor cursor;

        switch (match) {
            case LOGIN_TABLE:
                // 查询整个 Login 表
                Log.d("query", "在LOGIN_TABLE");
                cursor = loginDao.queryAll();
                break;
            case LOGIN_TABLE_ROW:

            case LOGIN_SQL_ROW:
                // 查询指定 ID 的 Login
                Log.d("query", "在LOGIN_ROW");
                cursor = null;
                break;

            case LOGIN_SQL:
                Log.d("query", "在LOGIN_SQL");
                String baseQuery = "SELECT %s FROM Login%s%s";
                String projectionClause = (projection != null && projection.length > 0) ? TextUtils.join(",", projection) : "*";
                String selectionClause = (!TextUtils.isEmpty(selection)) ? " WHERE " + selection : "";
                String sortOrderClause = (!TextUtils.isEmpty(sortOrder)) ? " ORDER BY " + sortOrder : "";
                String queryString = String.format(baseQuery, projectionClause, selectionClause, sortOrderClause);
                Log.d("query", "生成了SQL语句："+queryString);
                SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString, selectionArgs);
                cursor = loginDao.queryByExecSql(query);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletedRows = 0;
        int match = uriMatcher.match(uri);
        switch (match) {
            case LOGIN_TABLE:
                deletedRows = loginDao.deleteAll();
                break;
            case LOGIN_TABLE_ROW:
            case LOGIN_SQL_ROW:
                break;
            case LOGIN_SQL:
                String baseDeleteQuery = "DELETE FROM Login%s";
                String baseQuery = "SELECT * FROM Login%s";
                String selectionClause = (!TextUtils.isEmpty(selection)) ? " WHERE " + selection : "";
                String deleteQueryString = String.format(baseDeleteQuery,  selectionClause);
                String queryString = String.format(baseQuery,  selectionClause);
                SupportSQLiteQuery deleteQuery = new SimpleSQLiteQuery(deleteQueryString, selectionArgs);
                SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString, selectionArgs);
                Cursor cursor = loginDao.queryByExecSql(query);
                deletedRows = cursor.getCount();
                loginDao.deleteByExecSql(deleteQuery);
                Log.d("delete", Integer.toString(deletedRows));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedRows;
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