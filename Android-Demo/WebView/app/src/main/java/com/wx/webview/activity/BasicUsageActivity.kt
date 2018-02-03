package com.wx.webview.activity

import android.databinding.ViewDataBinding
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.databinding.ActivityBasicUsageBinding

class BasicUsageActivity : BaseActivity() {

    private lateinit var binding: ActivityBasicUsageBinding

    override val layoutId: Int
        get() = R.layout.activity_basic_usage

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityBasicUsageBinding
    }

    override fun initData() {

    }

}
