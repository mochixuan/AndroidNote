package com.wx.designpatterns

import android.databinding.ViewDataBinding
import com.wx.designpatterns.base.BaseActivity
import com.wx.designpatterns.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding.btn1.setOnClickListener {

        }
        binding.btn2.setOnClickListener {

        }
        binding.btn3.setOnClickListener {

        }
        binding.btn4.setOnClickListener {

        }
        binding.btn5.setOnClickListener {

        }
    }


}
