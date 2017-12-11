package com.wx.dialogmaster

import android.databinding.ViewDataBinding
import com.wx.dialogmaster.activity.MaterialDesignActivity
import com.wx.dialogmaster.activity.Retrofit2Activity
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        
        binding.btn1.setOnClickListener {
            openActivity(MaterialDesignActivity::class.java)
        }

        binding.btn2.setOnClickListener {
            openActivity(Retrofit2Activity::class.java)
        }

    }

}
