package com.wx.hencoders.activity.one

import android.databinding.ViewDataBinding
import com.wx.gaodemaster.base.BaseActivity
import com.wx.hencoders.R
import com.wx.hencoders.databinding.ActivityView11Binding

class View11Activity : BaseActivity() {

    lateinit var binding: ActivityView11Binding

    override fun getLayouId(): Int {
        return R.layout.activity_view11
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityView11Binding
    }

    override fun initData() {

    }

}
