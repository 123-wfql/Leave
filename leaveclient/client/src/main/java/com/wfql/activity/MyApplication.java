package com.wfql.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.wfql.database.AdminDatabase;

import java.util.HashMap;

public class MyApplication extends Application {

    private static MyApplication mApp;

    //声明映射对象，当前全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();

    private AdminDatabase adminDatabase;


    public static MyApplication getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        adminDatabase = Room.databaseBuilder(this, AdminDatabase.class, "Admin")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        Log.d("wfql", "MyApplication onCreate");
    }

    public AdminDatabase getAdminDatabase(){
        return adminDatabase;
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
