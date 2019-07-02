package com.wx.acc.sample1

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.util.Log
import com.wx.acc.R
import com.wx.acc.base.BaseActivity
import com.wx.acc.databinding.ActivitySample1Binding

class Sample1Activity : BaseActivity() {

    private lateinit var binding: ActivitySample1Binding

    override val layoutId: Int
        get() = R.layout.activity_sample1

    override fun initData(binding: ViewDataBinding) {
        this.binding = binding as ActivitySample1Binding

        ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.application)
                .create(UserViewModel::class.java)
                .liveUser.observe(this, Observer {
                    Log.d(TAG,"===>>"+it.toString())
                })

        binding.btn1.setOnClickListener {

        }
    }

}
