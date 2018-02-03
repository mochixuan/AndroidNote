package com.wx.glidedemo1.adapter

import com.ppdl.remoteclient.adapter.base.BaseAdapter
import com.ppdl.remoteclient.adapter.viewholder.BaseViewHolder
import com.wx.glidedemo1.R
import com.wx.glidedemo1.data.ListBean
import com.wx.glidedemo1.databinding.ItemWechatBinding

/**
 * Created by wangxuan on 2018/1/24.
 */

class WeChatAdapter : BaseAdapter<ListBean>() {

    override val layoutId: Int
        get() = R.layout.item_wechat

    override fun onBindView(holder: BaseViewHolder, data: ListBean, position: Int) {
        val binding = holder.binding as ItemWechatBinding
        binding.wechat = data
        //数据Observable改变后，会在下一帧改变，如果需要立即执行这写下面这条
        //binding.executePendingBindings()
        //binding.setVariable(BR.wechat,data)
    }

}