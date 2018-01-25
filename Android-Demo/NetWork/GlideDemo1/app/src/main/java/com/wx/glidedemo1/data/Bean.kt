package com.wx.glidedemo1.data

/**
 * Created by wangxuan on 2018/1/24.
 */

data class ListBean(
        val firstImg: String,
        val id: String,
        val source: String,
        val title: String,
        val url: String,
        val mark: String
)

data class ResultBean(
        val totalPage: Int,
        val ps: Int,
        val pno: Int,
        val list: List<ListBean>
)

data class WeChatBean(
        val reason: String,
        val result: ResultBean,
        val error_code: Int
)