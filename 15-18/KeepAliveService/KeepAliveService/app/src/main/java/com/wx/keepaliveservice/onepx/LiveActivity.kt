package com.wx.keepaliveservice.onepx

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.wx.keepaliveservice.R

class LiveActivity : Activity() {

    val TAG = LiveActivity::class.java.simpleName
    var mReciver: ScreenBroadcastReceiver? = null

    companion object {

        private var mInstance: LiveActivity? = null
        private var mReciver: ScreenBroadcastReceiver? = null

        fun start(context: Context) {
            val intent = Intent(context.applicationContext,LiveActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.applicationContext.startActivity(intent)
        }

        fun release() {
            if (mInstance?.mReciver != null) {
                mInstance?.unregisterReceiver(mInstance?.mReciver)
            }
            mInstance?.finish()
        }

        fun register(activity: Activity) {
            try {
                if (mReciver==null) {
                    mReciver = ScreenBroadcastReceiver()
                }
                val filter = IntentFilter()
                filter.addAction(Intent.ACTION_SCREEN_ON)
                filter.addAction(Intent.ACTION_SCREEN_OFF)
                activity.registerReceiver(mReciver, filter)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)
        mInstance = this
        Log.d(TAG,"=================onCreate")

        var window = window;
        window.setGravity(Gravity.START or Gravity.TOP)
        var attributes = window.attributes;
        attributes.width = 1
        attributes.height = 1
        attributes.x = 0
        attributes.y = 0
        window.attributes = attributes
    }

    override fun onDestroy() {
        super.onDestroy()
        mInstance = null;
        Log.d(TAG,"=================onDestroy")
    }

}
