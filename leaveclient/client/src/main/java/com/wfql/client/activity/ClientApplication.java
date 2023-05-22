package com.wfql.client.activity;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;


import java.util.HashMap;

public class ClientApplication extends Application {

    private static ClientApplication mApp;

    //声明映射对象，当前全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();



    public static ClientApplication getInstance(){
        return mApp;
    }

    //启动app调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
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
