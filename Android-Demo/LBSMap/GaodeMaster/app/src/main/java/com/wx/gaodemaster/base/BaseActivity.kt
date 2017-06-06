package com.wx.gaodemaster.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(){

    abstract fun getLayouId(): Int

    abstract fun setDataBinding(binding: ViewDataBinding)

    abstract fun initData()

    var savedInstanceState: Bundle? = null      //兼容MapActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setDataBinding(DataBindingUtil.setContentView(this,getLayouId()))
        initData()
    }

    fun openActivity(activity: Class<out Activity>) {
        startActivity(Intent(this,activity))
    }

    fun showToast(data: String){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }

}