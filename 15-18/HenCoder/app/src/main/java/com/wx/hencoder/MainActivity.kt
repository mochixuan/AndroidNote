package com.wx.hencoder

import android.databinding.ViewDataBinding
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityMainBinding
import com.wx.hencoder.practice11.Practice11Activity
import com.wx.hencoder.practice12.Practice12Activity
import com.wx.hencoder.practice13.Practice13Activity
import com.wx.hencoder.practice14.Practice14Activity
import com.wx.hencoder.practice15.Practice15Activity
import com.wx.hencoder.practice16.Practice16Activity
import com.wx.hencoder.practice17.Practice17Activity
import com.wx.hencoder.practice18.Practice18Activity

class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding!!.btn1.setOnClickListener { openActivity(Practice11Activity::class.java) }
        binding!!.btn2.setOnClickListener { openActivity(Practice12Activity::class.java) }
        binding!!.btn3.setOnClickListener { openActivity(Practice13Activity::class.java) }
        binding!!.btn4.setOnClickListener { openActivity(Practice14Activity::class.java) }
        binding!!.btn5.setOnClickListener { openActivity(Practice15Activity::class.java) }
        binding!!.btn6.setOnClickListener { openActivity(Practice16Activity::class.java) }
        binding!!.btn7.setOnClickListener { openActivity(Practice17Activity::class.java) }
        binding!!.btn8.setOnClickListener { openActivity(Practice18Activity::class.java) }
    }


}
