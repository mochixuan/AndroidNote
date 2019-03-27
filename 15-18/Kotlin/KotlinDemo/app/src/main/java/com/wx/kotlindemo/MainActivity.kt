package com.wx.kotlindemo

import android.view.View
import android.widget.Button
import com.wx.kotlindemo.activity.AnnotationActivity
import com.wx.kotlindemo.activity.EasyActivity
import com.wx.kotlindemo.activity.GrammarActivity
import com.wx.kotlindemo.activity.ListActivity
import com.wx.kotlindemo.base.BaseActivity

/**
 * 一切从简，用最初的方式熟悉每一个方法
 *                                   ~ mochixuan
 * */

class MainActivity : BaseActivity() , View.OnClickListener{

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        findViewById(R.id.btn_1).setOnClickListener(this)
        findViewById(R.id.btn_2).setOnClickListener(this)
        findViewById(R.id.btn_3).setOnClickListener(this)
        findViewById(R.id.btn_4).setOnClickListener(this)
        findViewById(R.id.btn_5).setOnClickListener(this)
        findViewById(R.id.btn_6).setOnClickListener(this)
        findViewById(R.id.btn_7).setOnClickListener(this)
        findViewById(R.id.btn_8).setOnClickListener(this)
        findViewById(R.id.btn_9).setOnClickListener(this)
        findViewById(R.id.btn_10).setOnClickListener(this)
    }

    override fun initData() {
        (findViewById(R.id.btn_1) as Button).text = "语法"
        (findViewById(R.id.btn_2) as Button).text = "集合"
        (findViewById(R.id.btn_3) as Button).text = "简单"
        (findViewById(R.id.btn_4) as Button).text = "注解"
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_1 -> openActivity(GrammarActivity::class.java)
            R.id.btn_2 -> openActivity(ListActivity::class.java)
            R.id.btn_3 -> openActivity(EasyActivity::class.java)
            R.id.btn_4 -> openActivity(AnnotationActivity::class.java)
            else -> showToast("UnKnown") //相当于switch default
        }
    }

}
