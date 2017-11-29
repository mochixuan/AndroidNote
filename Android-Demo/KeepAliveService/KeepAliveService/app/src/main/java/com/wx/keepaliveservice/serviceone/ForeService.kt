package com.wx.keepaliveservice.serviceone

import android.app.Notification
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat

class ForeService : Service() {

    private val FORESERVICE_PID = android.os.Process.myPid()
    private var mConnection: AssistServiceConnection? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT < 18) {
            this.startForeground(FORESERVICE_PID, android.app.Notification())
            return
        }

        if (null == mConnection) {
            mConnection = AssistServiceConnection()
        }

        bindService(Intent(this, AssistService::class.java), mConnection, Service.BIND_AUTO_CREATE)
    }

    fun getNotification(): Notification {
        val notification = NotificationCompat.Builder(this).build()
        return notification
    }

    private inner class AssistServiceConnection : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, binder: IBinder) {

            val assistService = (binder as AssistService.LocalBinder).service
            this@ForeService.startForeground(FORESERVICE_PID, getNotification())
            assistService.startForeground(FORESERVICE_PID, getNotification())
            assistService.stopForeground(true)

            this@ForeService.unbindService(mConnection)
            mConnection = null
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

}
