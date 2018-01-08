package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityStreamBinding

class StreamActivity : BaseActivity() {

    private lateinit var binding: ActivityStreamBinding

    override val layoutId: Int
        get() = R.layout.activity_stream

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityStreamBinding
    }

    override fun initData() {
        binding.btn1.setOnClickListener {

        }

    }



}
