package com.wx.review.activity

import android.databinding.ViewDataBinding
import com.wx.review.R
import com.wx.review.base.BaseActivity
import com.wx.review.databinding.ActivityRetrofitBinding

class RetrofitActivity : BaseActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override val layoutId: Int
        get() = R.layout.activity_retrofit

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityRetrofitBinding
    }

    override fun initData() {
        
    }

}
