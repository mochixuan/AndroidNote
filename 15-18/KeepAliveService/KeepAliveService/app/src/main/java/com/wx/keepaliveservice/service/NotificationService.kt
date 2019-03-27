package com.wx.keepaliveservice.service

import android.annotation.TargetApi
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class NotificationService : NotificationListenerService() {

    val TAG = NotificationService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "==================================onCreate=")
    }

    //当有新通知到来时会回调
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        Log.d(TAG, "==================================onNotificationPosted=")
        Log.d(TAG,"====onNotificationPosted>>"+sbn.packageName)
    }

    //当有通知移除时会回调
    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d(TAG, "==================================onNotificationRemoved=")
    }

    //当 NotificationListenerService 是可用的并且和通知管理器连接成功时回调
    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d(TAG, "==================================onListenerConnected=")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "==================================onDestroy=")
    }

}
