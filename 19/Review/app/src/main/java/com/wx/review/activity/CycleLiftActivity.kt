package com.wx.review.activity

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.wx.review.R

class CycleLiftActivity : Activity() {

    private val TAG: String =  this.javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,TAG+" onCreate");
        setContentView(R.layout.activity_cycle_lift)

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,TAG+" onRestart");
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,TAG+" onStart");
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,TAG+" onResume");
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,TAG+" onSaveInstanceState");
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG,TAG+" onRestoreInstanceState");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,TAG+" onPause");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,TAG+" onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,TAG+" onDestroy");
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG,TAG+" onConfigurationChanged");
    }

}
