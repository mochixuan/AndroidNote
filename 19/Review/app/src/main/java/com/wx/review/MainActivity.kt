package com.wx.review

import android.databinding.ViewDataBinding
import android.util.Log
import com.wx.review.activity.CycleLiftActivity
import com.wx.review.activity.KotlinActivity
import com.wx.review.activity.RetrofitActivity
import com.wx.review.activity.ServiceActivity
import com.wx.review.base.BaseActivity
import com.wx.review.databinding.ActivityMainBinding
import com.wx.review.mvp.LoginActivity

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
        binding.btnLift.setOnClickListener{ openActivity(CycleLiftActivity::class.java) }
        binding.btnService.setOnClickListener { openActivity(ServiceActivity::class.java) }
        binding.btnMvp.setOnClickListener { openActivity(LoginActivity::class.java) }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,TAG+" onResume");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,TAG+" onPause");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,TAG+" onStop");
    }

}
