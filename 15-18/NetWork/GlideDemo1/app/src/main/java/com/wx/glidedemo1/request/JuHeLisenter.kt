package com.wx.glidedemo1.request

import com.wx.glidedemo1.data.WeChatBean

/**
 * Created by wangxuan on 2018/2/2.
 */

interface JuHeLisenter {

    fun onSuccess(bean: WeChatBean)

    fun onFailure()

}
