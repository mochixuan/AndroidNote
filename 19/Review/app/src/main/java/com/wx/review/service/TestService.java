package com.wx.review.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    private String TAG = this.getClass().getSimpleName();

    private WIBinder mWIBinder;


    public class WIBinder extends Binder {
        public String name = "Mo Chi Xuan";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"===========>>>onCreate");
        mWIBinder = new WIBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"===========>>>onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"===========>>>onBind");
        return new WIBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"===========>>>onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"===========>>>onDestroy");
    }
}
