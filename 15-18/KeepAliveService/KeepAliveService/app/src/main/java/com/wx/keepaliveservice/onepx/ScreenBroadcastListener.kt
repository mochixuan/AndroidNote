package com.wx.keepaliveservice.onepx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ScreenBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (Intent.ACTION_SCREEN_ON == intent.action) { // 开屏
                Log.d("ScreenBroadcastListener","=================================开屏>>")
                LiveActivity.start(context)
            } else if (Intent.ACTION_SCREEN_OFF == intent.action) { // 锁屏
                Log.d("ScreenBroadcastListener","=================================锁屏>>")
                LiveActivity.release()
            }
        }

}
