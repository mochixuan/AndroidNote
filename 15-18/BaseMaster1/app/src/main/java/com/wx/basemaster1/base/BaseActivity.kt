package com.wx.basemaster1.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes

abstract class BaseActivity : Activity() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun setDataBinding(binding: ViewDataBinding)

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding(DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId))
        initData()
    }

    fun openActivity(other: Class<out Activity>) {
        val intent = Intent(this, other)
        startActivity(intent)
    }

}
