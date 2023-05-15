package com.wfql.server.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.wfql.server.database.LoginDatabase;

import java.util.HashMap;

public class MyApplication extends Application {

    private static MyApplication mApp;

    private LoginDatabase loginDatabase;

    public static MyApplication getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        loginDatabase = Room.databaseBuilder(this, LoginDatabase.class, "Login")
                //允许迁移数据库和允许数据库在主线程操作
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        Log.d("wfql", "MyApplication onCreate");
    }

    public LoginDatabase getLoginDatabase(){
        return loginDatabase;
    }

    public static LoginDatabase getLoginDatabaseInstance() {
        LoginDatabase loginDatabaseInstance = getInstance().getLoginDatabase();
        if (loginDatabaseInstance == null) {
            synchronized (MyApplication.class) {
                loginDatabaseInstance = getInstance().getLoginDatabase();
                if (loginDatabaseInstance == null) {
                    loginDatabaseInstance = Room.databaseBuilder(getInstance(), LoginDatabase.class, "Login")
                            .addMigrations()
                            .allowMainThreadQueries()
                            .build();
                    getInstance().loginDatabase = loginDatabaseInstance;
                }
            }
        }
        return loginDatabaseInstance;
    }


    //APP终止调用
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //横屏竖屏切换
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
