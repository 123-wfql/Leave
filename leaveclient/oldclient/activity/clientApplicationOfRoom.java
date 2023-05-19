package com.wfql.client.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.wfql.client.databaseNoNeed.LoginDatabaseOfRoom;

import java.util.HashMap;

public class clientApplicationOfRoom extends Application {

    private static clientApplicationOfRoom mApp;

    //声明映射对象，当前全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();

    private LoginDatabaseOfRoom loginDatabase;


    public static clientApplicationOfRoom getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        loginDatabase = Room.databaseBuilder(this, LoginDatabaseOfRoom.class, "Login")
                //允许迁移数据库和允许数据库在主线程操作
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        Log.d("wfql", "loginDatabase onCreate");
    }

    public LoginDatabaseOfRoom getLoginDatabase() {
        return loginDatabase;
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
