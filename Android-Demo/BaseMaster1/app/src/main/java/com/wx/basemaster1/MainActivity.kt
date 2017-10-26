package com.wx.basemaster1

import android.databinding.ViewDataBinding
import com.wx.basemaster1.activity.ContrainLayout1Activity
import com.wx.basemaster1.activity.ContrainLayoutActivity
import com.wx.basemaster1.activity.KotlinActivity
import com.wx.basemaster1.base.BaseActivity
import com.wx.basemaster1.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding!!.btnConstrain.setOnClickListener { openActivity(ContrainLayoutActivity::class.java) }
        binding!!.btnConstrain1.setOnClickListener { openActivity(ContrainLayout1Activity::class.java) }
        binding!!.btnKotlin.setOnClickListener { openActivity(KotlinActivity::class.java) }
        binding!!.btnNetwork.setOnClickListener { openActivity(KotlinActivity::class.java) }
    }

}
