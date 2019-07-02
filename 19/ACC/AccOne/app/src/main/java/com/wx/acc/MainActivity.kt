package com.wx.acc

import android.databinding.ViewDataBinding
import com.wx.acc.base.BaseActivity
import com.wx.acc.databinding.ActivityMainBinding
import com.wx.acc.sample1.Sample1Activity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initData(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding

        binding.sample1.setOnClickListener { openActivity(Sample1Activity::class.java) }
    }

}
