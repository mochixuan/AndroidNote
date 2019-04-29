package com.wx.review

import android.databinding.ViewDataBinding
import com.wx.review.activity.KotlinActivity
import com.wx.review.activity.RetrofitActivity
import com.wx.review.base.BaseActivity
import com.wx.review.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding.btnRetrofit.setOnClickListener { openActivity(RetrofitActivity::class.java) }
        binding.btnKotlin.setOnClickListener { openActivity(KotlinActivity::class.java) }
    }

}
