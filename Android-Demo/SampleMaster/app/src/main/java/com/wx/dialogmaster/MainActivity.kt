package com.wx.dialogmaster

import android.databinding.ViewDataBinding
import com.wx.dialogmaster.activity.MaterialDesignActivity
import com.wx.dialogmaster.activity.OkgoBaseActivity
import com.wx.dialogmaster.activity.OkgoDownActivity
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
            openActivity(OkgoBaseActivity::class.java)
        }

        binding.btn3.setOnClickListener {
            openActivity(OkgoDownActivity::class.java)
        }

    }

}
