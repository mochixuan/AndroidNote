package com.wx.review.base

import android.app.Activity
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

interface BaseView {

    @get: LayoutRes
    val layoutId: Int

    fun setDataBinding(binding: ViewDataBinding)

    fun initData()

    fun openActivity(activity: Class<out Activity>)

    fun showToast(data: String)
}
