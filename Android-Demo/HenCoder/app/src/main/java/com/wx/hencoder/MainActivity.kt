package com.wx.hencoder

import android.databinding.ViewDataBinding
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityMainBinding
import com.wx.hencoder.practice11.Practice11Activity
import com.wx.hencoder.practice12.Practice12Activity

class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding!!.btn1.setOnClickListener { openActivity(Practice11Activity::class.java) }
        binding!!.btn2.setOnClickListener { openActivity(Practice12Activity::class.java) }
    }


}
