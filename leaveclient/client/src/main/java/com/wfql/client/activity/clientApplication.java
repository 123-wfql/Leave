package com.wfql.client.activity;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.wfql.client.databaseNoNeed.LoginDatabaseOfRoom;

import java.util.HashMap;

public class clientApplication extends Application {

    private static clientApplication mApp;

    //声明映射对象，当前全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();

    private LoginDatabaseOfRoom loginDatabase;


    public static clientApplication getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
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
