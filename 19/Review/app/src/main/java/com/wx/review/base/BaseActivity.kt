package com.wx.review.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.widget.Toast

abstract class BaseActivity : Activity(), BaseView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding(DataBindingUtil.setContentView<ViewDataBinding>(this,layoutId))
        initData()
    }

    override fun openActivity(activity: Class<out Activity>) {
        startActivity(Intent(this,activity))
    }

    override fun showToast(data: String) {
        if (data === null) return

        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }

}