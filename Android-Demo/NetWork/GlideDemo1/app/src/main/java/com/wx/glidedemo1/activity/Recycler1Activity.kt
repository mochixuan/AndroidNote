package com.wx.glidedemo1.activity

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import com.wx.glidedemo1.R
import com.wx.glidedemo1.adapter.WeChatAdapter1
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.data.WeChatBean
import com.wx.glidedemo1.databinding.ActivityRecycler1Binding
import com.wx.glidedemo1.request.JuHeLisenter
import com.wx.glidedemo1.request.JuHeMananger

class Recycler1Activity : BaseActivity() {

    private lateinit var binding: ActivityRecycler1Binding
    private lateinit var mAdapter: WeChatAdapter1
    private var pno = 1
    private val ps = 50

    override val layoutId: Int
        get() = R.layout.activity_recycler1

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityRecycler1Binding
    }

    override fun initData() {
        mAdapter = WeChatAdapter1()
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.swipeRefresh.setOnRefreshListener {
            JuHeMananger.getWetChatSelect(pno,ps,mCallBack)
        }
    }

    val mCallBack = object : JuHeLisenter {
        override fun onSuccess(bean: WeChatBean) {
            mAdapter.addDatas(bean.result.list.toMutableList(),true,true)
            pno = pno.inc()
            binding.swipeRefresh.isRefreshing = false
            showToast("成功")
        }
        override fun onFailure() {
            binding.swipeRefresh.isRefreshing = false
            showToast("失败")
        }

    }

}
