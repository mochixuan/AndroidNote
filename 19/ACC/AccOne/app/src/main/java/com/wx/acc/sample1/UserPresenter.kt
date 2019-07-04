package com.wx.acc.sample1

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class UserPresenter : LifecycleObserver{

    private val TAG = this.javaClass.simpleName

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.d(TAG,"==========onStart>>")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.d(TAG,"==========onDestroy>>")
    }

}