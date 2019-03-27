package com.wx.webview.jsinterface

import android.webkit.JavascriptInterface


/**
 * Created by wangxuan on 2018/2/6.
 */

// 继承自Object类
open class JsToAndroid : Any() {

    private var mJsToAndroidListener: JsToAndroidListener? = null

    fun setJsToAndroidListener(listener: JsToAndroidListener) {
        this.mJsToAndroidListener = listener
    }

    //thread回馈的注意了
    @JavascriptInterface
    fun hello(msg: String): String {
        mJsToAndroidListener?.hello(msg)
        System.out.println("==============Js 调用 Android "+msg+"  "+Thread.currentThread())
        return "你好 Js 我是 Android"
    }

}

interface JsToAndroidListener {
    fun hello(msg: String)
}