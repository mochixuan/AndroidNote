package com.wx.dialogmaster.activity1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wx.dialogmaster.R

class TwoActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        Log.w(TAG,">>>>>>>>>>onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.w(TAG,">>>>>>>>>>onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG,">>>>>>>>>>onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG,">>>>>>>>>>onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG,">>>>>>>>>>onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG,">>>>>>>>>>onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG,">>>>>>>>>>onDestroy")
    }

}
