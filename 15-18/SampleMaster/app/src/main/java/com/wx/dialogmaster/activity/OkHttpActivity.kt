package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import android.util.Log
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityOkhttpBinding
import okhttp3.*
import java.io.IOException

class OkHttpActivity : BaseActivity() {

    private lateinit var binding: ActivityOkhttpBinding
    private val TAG = this.javaClass.simpleName

    override val layoutId: Int
        get() = R.layout.activity_okhttp

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityOkhttpBinding
    }

    override fun initData() {

        binding.btnDown.setOnClickListener {
            getBase1()
        }

    }


    private fun getBase1() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url("http://www.baidu.com")
                .method("GET",null)
                .build()
        okHttpClient.newCall(request)
                .enqueue(object : Callback{

                    override fun onResponse(call: Call?, response: Response?) {
                        Log.d(TAG,"===================="+response!!.isSuccessful+"  "+Thread.currentThread())
                    }

                    override fun onFailure(call: Call?, e: IOException?) {
                        Log.d(TAG,"====================onFailure"+"  "+Thread.currentThread())
                    }

                })
    }

}
