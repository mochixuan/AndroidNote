package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import com.wx.dialogmaster.R
import com.wx.dialogmaster.adapter.FileDownAdapter
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityRetrofit2Binding

class Retrofit2Activity : BaseActivity() {

    private lateinit var binding: ActivityRetrofit2Binding;
    private lateinit var mAdapter: FileDownAdapter

    override val layoutId: Int
        get() = R.layout.activity_retrofit2

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityRetrofit2Binding
    }

    override fun initData() {
        val layoutManager = LinearLayoutManager(this)
        mAdapter = FileDownAdapter()
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mAdapter
    }

}
