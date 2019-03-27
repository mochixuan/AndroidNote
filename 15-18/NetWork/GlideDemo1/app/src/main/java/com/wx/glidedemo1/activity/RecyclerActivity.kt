package com.wx.glidedemo1.activity

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import com.wx.glidedemo1.R
import com.wx.glidedemo1.adapter.WeChatAdapter
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.data.WeChatBean
import com.wx.glidedemo1.databinding.ActivityPractice2Binding
import com.wx.glidedemo1.request.JuHeLisenter
import com.wx.glidedemo1.request.JuHeMananger

class RecyclerActivity : BaseActivity() {

    private lateinit var binding: ActivityPractice2Binding
    private var pno = 1
    private val ps = 10
    private lateinit var mAdapter: WeChatAdapter

    override val layoutId: Int
        get() = R.layout.activity_practice2

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice2Binding
    }

    override fun initData() {
        mAdapter = WeChatAdapter()
        val manager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = manager
        //binding.recyclerView.addItemDecoration(DividerItemDecorationOne(this))
        binding.swipeRefresh.setOnRefreshListener {
            JuHeMananger.getWetChatSelect(pno,ps,mListener)
        }
        JuHeMananger.getWetChatSelect(pno,ps,mListener)
    }

    private val mListener = object: JuHeLisenter{
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
