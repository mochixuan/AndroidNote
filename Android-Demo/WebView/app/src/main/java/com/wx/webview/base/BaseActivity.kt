package com.wx.glidedemo1.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by wangxuan on 2018/1/24.
 */

abstract class BaseActivity : AppCompatActivity() {

    @get: LayoutRes
    abstract val layoutId: Int

    abstract fun setDataBinding(binding: ViewDataBinding)

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding(DataBindingUtil.setContentView(this,layoutId))
        initData()
    }

    fun openActivity(otherActivity: Class<out Activity>) {
        startActivity(Intent(this,otherActivity))
    }

    fun showToast(data: String) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }

}
