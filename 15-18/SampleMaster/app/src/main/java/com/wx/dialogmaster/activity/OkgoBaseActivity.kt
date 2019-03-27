package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityOkgobaseBinding

class OkgoBaseActivity : BaseActivity() {

    private lateinit var binding: ActivityOkgobaseBinding

    override val layoutId: Int
        get() = R.layout.activity_okgobase

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityOkgobaseBinding
    }

    override fun initData() {
        binding.btn1.setOnClickListener {

        }
    }

}
