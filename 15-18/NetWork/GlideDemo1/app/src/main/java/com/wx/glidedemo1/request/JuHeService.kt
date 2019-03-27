package com.wx.glidedemo1.request

import com.wx.glidedemo1.data.WeChatBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wangxuan on 2018/2/2.
 */
interface JuHeService {

    @GET("weixin/query")
    fun getWetChatSelect(@Query("pno") pno: Int,
                         @Query("ps") ps: Int,
                         @Query("dtype") dtype: String,
                         @Query("key") key: String
    ): Observable<WeChatBean>

}