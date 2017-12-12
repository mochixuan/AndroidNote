package com.wx.dialogmaster.base

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer

/**
 * Created by wangxuan on 2017/12/11.
 */

public abstract class BaseActivity : AppCompatActivity(){

    @get: LayoutRes
    abstract val layoutId: Int;

    abstract fun setDataBinding(binding: ViewDataBinding);

    abstract fun initData();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding(DataBindingUtil.setContentView(this,layoutId))
        initPermission()
        initData()
    }

    private fun initPermission() {
        val rxPermission = RxPermissions(this)
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(Consumer { granted-> if (!granted) finish() })
    }

    fun openActivity(activity: Class<out Activity>) {
        startActivity(Intent(this,activity));
    }

    fun showToast(data: String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
    }

}