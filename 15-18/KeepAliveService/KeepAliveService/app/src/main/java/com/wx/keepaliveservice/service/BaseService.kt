package com.wx.keepaliveservice.service

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log


class BaseService : Service() {

    val TAG = BaseService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"==================================onCreate=")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"==================================onStartCommand=")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG,"==================================onBind=")
        return null
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.d(TAG,"==================================unbindService=")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"==================================onDestroy=")
    }

}
