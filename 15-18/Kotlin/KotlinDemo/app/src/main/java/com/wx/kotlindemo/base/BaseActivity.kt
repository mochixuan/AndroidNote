package com.wx.kotlindemo.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * base
 * */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    fun openActivity(activity: Class<out Activity>) {
        val intent: Intent = Intent(this,activity);
        startActivity(intent)
    }

    fun showToast(data: String?) {
        if (data == null) {
            Toast.makeText(this,"数据为空",Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
        }
    }

    //-----扩展-----
    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

}
