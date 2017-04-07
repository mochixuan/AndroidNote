package com.wx.realmdemo.application;

import android.app.Application;

import com.wx.realmdemo.manager.RealmManager;

public class MiApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RealmManager.getInstance().init(this);
    }

}
