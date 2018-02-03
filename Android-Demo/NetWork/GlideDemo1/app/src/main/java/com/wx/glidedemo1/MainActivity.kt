package com.wx.glidedemo1

import android.Manifest
import android.databinding.ViewDataBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wx.glidedemo1.activity.*
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        initPermission()

        binding.btn1.setOnClickListener {
            openActivity(Practice1Activity::class.java)
        }
        binding.btn2.setOnClickListener {
            openActivity(RecyclerActivity::class.java)
        }
        binding.btn3.setOnClickListener {
            openActivity(DataBindingActivity::class.java)
        }
        binding.btn4.setOnClickListener {
            openActivity(DataBinding1Activity::class.java)
        }
        binding.btn5.setOnClickListener {
            openActivity(Recycler1Activity::class.java)
        }
    }

    private fun initPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(io.reactivex.functions.Consumer { aBoolean ->
                    if (!aBoolean) finish()
                })
    }


}
