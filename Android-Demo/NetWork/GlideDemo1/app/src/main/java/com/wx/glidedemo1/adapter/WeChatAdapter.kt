package com.wx.glidedemo1.adapter

import com.ppdl.remoteclient.adapter.base.BaseAdapter
import com.ppdl.remoteclient.adapter.viewholder.BaseViewHolder
import com.wx.glidedemo1.R
import com.wx.glidedemo1.data.WeChatBean
import com.wx.glidedemo1.databinding.ItemWechatBinding

/**
 * Created by wangxuan on 2018/1/24.
 */

class WeChatAdapter : BaseAdapter<WeChatBean>() {

    override val layoutId: Int
        get() = R.layout.item_wechat

    override fun onBindView(holder: BaseViewHolder, data: WeChatBean, position: Int) {
       val binding = holder.binding as ItemWechatBinding
    }

}