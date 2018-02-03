package com.wx.glidedemo1.adapter

import com.ppdl.remoteclient.adapter.base.BaseAdapter
import com.ppdl.remoteclient.adapter.viewholder.BaseViewHolder
import com.wx.glidedemo1.R
import com.wx.glidedemo1.data.ListBean
import com.wx.glidedemo1.databinding.ItemWechatBinding

/**
 * Created by wangxuan on 2018/1/24.
 */

class WeChatAdapter1 : BaseAdapter<ListBean>() {

    override val layoutId: Int
        get() = R.layout.item_wechat

    override fun onBindView(holder: BaseViewHolder, data: ListBean, position: Int) {
        val binding = holder.binding as ItemWechatBinding
        binding.wechat = data
    }

}