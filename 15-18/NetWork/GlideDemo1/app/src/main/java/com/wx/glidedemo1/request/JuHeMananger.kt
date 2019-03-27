package com.wx.glidedemo1.request

import com.wx.glidedemo1.data.WeChatBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wangxuan on 2018/2/2.
 */
class JuHeMananger {

    companion object {

        fun getWetChatSelect(pno: Int,ps: Int,listener: JuHeLisenter) {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            val juHeService = retrofit.create(JuHeService::class.java)
            juHeService.getWetChatSelect(pno,ps,"json","97ccfd2896c2fe7d5662510254995d63")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (object : Observer<WeChatBean> {
                        override fun onSubscribe(d: Disposable) {

                        }
                        override fun onNext(t: WeChatBean) {
                            listener?.onSuccess(t)
                        }
                        override fun onComplete() {

                        }
                        override fun onError(e: Throwable) {
                            listener?.onFailure()
                        }
                    })
        }

    }

}