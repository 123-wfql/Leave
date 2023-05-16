package com.wfql.server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.room.Room;

import com.wfql.server.dao.LoginDao;
import com.wfql.server.database.LeaveSysDB;
import com.wfql.server.entity.Login;

public class LoginProvider extends ContentProvider {

    private LeaveSysDB leaveSysDB;
    private LoginDao loginDao;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        leaveSysDB = Room.databaseBuilder(context, LeaveSysDB.class, "LeaveSysDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations()
                .build();
        loginDao = leaveSysDB.loginDao();
        Log.d("wfql", "loginDao in provider onCreate");
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
        if (projection == null){
            Cursor cursor = loginDao.query(selection, selectionArgs, sortOrder);
            return cursor;
        } else {
            Cursor cursor = loginDao.query(projection, selection, selectionArgs, sortOrder);
            return cursor;
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d("wfql", "provider delete");
        int deleteColumn = loginDao.delete(selection, selectionArgs);
        return deleteColumn;
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