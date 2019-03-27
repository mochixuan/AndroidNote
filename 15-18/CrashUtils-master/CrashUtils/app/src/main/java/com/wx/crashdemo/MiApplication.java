package com.wx.crashdemo;

import android.app.Application;

import com.wx.crashlibrary.manager.CrashManager;

public class MiApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CrashManager.getInstance().init();
    }

}
