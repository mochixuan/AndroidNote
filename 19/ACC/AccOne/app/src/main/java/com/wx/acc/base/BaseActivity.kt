package com.wx.acc.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity : AppCompatActivity() {

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