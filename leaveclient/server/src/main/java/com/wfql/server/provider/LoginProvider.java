package com.wfql.server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.wfql.server.activity.MainActivity;
import com.wfql.server.activity.MyApplication;
import com.wfql.server.dao.LoginDao;
import com.wfql.server.entity.Login;

public class LoginProvider extends ContentProvider {
//    public LoginProvider() {
//    }


    private LoginDao loginDao;

    @Override
    public boolean onCreate() {
        // 1TODO: Implement this to initialize your content provider on startup.
        if (MyApplication.getInstance() != null){
            loginDao = MyApplication.getLoginDatabaseInstance().loginDao();
            Log.d("wfql", "Provider onCreate!");
        }
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

        Login login = new Login();
        login.setLoginId(values.getAsString("loginId"));
        login.setLoginPwd(values.getAsString("loginPwd"));
        //login.setRemember(values.getAsBoolean("remember"));
        loginDao.insert(login);
        return uri;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO: Implement this to handle requests to delete one or more rows.
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