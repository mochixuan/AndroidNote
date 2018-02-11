package com.wx.threadmaster

import android.databinding.ViewDataBinding
import com.wx.glidedemo1.base.BaseActivity
import com.wx.threadmaster.activity.BasicUsageActivity
import com.wx.threadmaster.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding.btn1.setOnClickListener {
            openActivity(BasicUsageActivity::class.java)
        }
    }

}
