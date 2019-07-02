package com.wx.acc.base

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat.startActivity

abstract class BaseActivity : Activity() {

    protected val TAG = javaClass.simpleName

    @get: LayoutRes
    abstract val layoutId: Int


    abstract fun initData(binding: ViewDataBinding);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData(DataBindingUtil.setContentView(this,layoutId))
    }

    fun openActivity(otherActivity: Class<out Activity> ) {
        startActivity(Intent(this,otherActivity))
    }


}