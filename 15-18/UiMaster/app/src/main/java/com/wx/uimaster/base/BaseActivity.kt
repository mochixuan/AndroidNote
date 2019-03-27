package com.wx.uimaster.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun setDataBinding(binding: ViewDataBinding)

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding(DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId()))
        initData()
    }

    fun openActivity(activity: Class<out Activity>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    fun showToast(data: String) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(this,getString(stringRes),Toast.LENGTH_SHORT).show()
    }

}
