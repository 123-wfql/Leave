package com.wfql.server.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.wfql.server.dao.LoginDao;
import com.wfql.server.database.LeaveSysDB;

public class serverApplication extends Application {

    private static serverApplication mApp;

    private LeaveSysDB loginDatabase;
    private static LoginDao loginDao;

    public static serverApplication getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Log.d("wfql", "Application onCreate");
        LeaveSysDB leaveSysDB = Room.databaseBuilder(mApp, LeaveSysDB.class, "LeaveSysDB")
                .build();
        loginDao = leaveSysDB.loginDao();
        Log.d("wfql", "loginDao in app create");

    }

    public LeaveSysDB getLoginDatabase(){

        return loginDatabase;
    }

    public static LoginDao getLoginDao(){
        return loginDao;
    }

    public static LeaveSysDB getLoginDatabaseInstance() {
        LeaveSysDB loginDatabaseInstance = getInstance().getLoginDatabase();
        if (loginDatabaseInstance == null) {
            synchronized (serverApplication.class) {
                loginDatabaseInstance = getInstance().getLoginDatabase();
                if (loginDatabaseInstance == null) {
                    loginDatabaseInstance = Room.databaseBuilder(getInstance(), LeaveSysDB.class, "Login")
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
