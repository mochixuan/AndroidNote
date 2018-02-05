package com.wx.webview

import android.databinding.ViewDataBinding
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.activity.BasicUsageActivity
import com.wx.webview.activity.JsBridgeActivity
import com.wx.webview.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding.btnBasicUsage.setOnClickListener {
            openActivity(BasicUsageActivity::class.java)
        }
        binding.btnJsBridge.setOnClickListener {
            openActivity(JsBridgeActivity::class.java)
        }
    }


}
