package com.wx.hencoder

import android.databinding.ViewDataBinding
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityMainBinding
import com.wx.hencoder.practice11.Practice11Activity
import com.wx.hencoder.practice12.Practice12Activity
import com.wx.hencoder.practice13.Practice13Activity
import com.wx.hencoder.pratice14.Practice14Activity

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
        binding!!.btn3.setOnClickListener { openActivity(Practice13Activity::class.java) }
        binding!!.btn4.setOnClickListener { openActivity(Practice14Activity::class.java) }
    }


}
