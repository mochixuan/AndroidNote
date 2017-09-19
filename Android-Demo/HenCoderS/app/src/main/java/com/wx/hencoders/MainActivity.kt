package com.wx.hencoders

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import com.wx.gaodemaster.base.BaseActivity
import com.wx.hencoders.activity.one.View11Activity
import com.wx.hencoders.adapter.module.MainAdapter
import com.wx.hencoders.databinding.ActivityMainBinding
import com.wx.hencoders.module.ActivityModule

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun getLayouId(): Int {
        return R.layout.activity_main
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        val manager = LinearLayoutManager(this);
        binding.recyclerView.layoutManager = manager
        val mAdapter = MainAdapter()
        binding.recyclerView.adapter = mAdapter
        mAdapter.setMainListener {
            activity -> openActivity(activity)
        }
        val datas = ArrayList<ActivityModule>()
        datas.add(ActivityModule(View11Activity::class.java,"自定义View1-1绘制基础"))
        mAdapter.addDatas(datas,false)
    }


}
