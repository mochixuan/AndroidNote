package com.wx.webview.activity

import android.databinding.ViewDataBinding
import android.os.Build
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.constant.DataConstant
import com.wx.webview.databinding.ActivitySafeBinding

class SafeActivity : BaseActivity() {

    private lateinit var binding: ActivitySafeBinding

    override val layoutId: Int
        get() = R.layout.activity_safe

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivitySafeBinding
    }

    override fun initData() {

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true

        //密码会被明文保到  包名/databases/webview.db
        settings.savePassword = false     //不保存,密码，或者js自动加密也行就可以不加

        /**
         * 使用 file 域加载的 js代码能够使用进行同源策略跨域访问，从而导致隐私信息泄露
         * 1. 同源策略跨域访问：对私有目录文件进行访问: 应该是自己包名下的
         * 2. 针对 IM 类产品，泄露的是聊天信息、联系人等等
         * 3. 针对浏览器类软件，泄露的是cookie 信息泄露
         * 4. 移动版的 Chrome 默认禁止加载 file 协议的文件
         *
         * 但同时也限制了 WebView 的功能，使其不能加载本地的 html 文件
         */
        settings.allowFileAccess = false  //看情况加载
        /*
            setAllowFileAccess(true);
            // 禁止 file 协议加载 JavaScript
            if (url.startsWith("file://") {
                setJavaScriptEnabled(false);
            } else {
                setJavaScriptEnabled(true);
            }
        */

        /**
         * 设置是否允许通过 file url 加载的 Js代码读取其他的本地文件
         * 在Android 4.1前默认允许
         * 在Android 4.1后默认禁止
         * 当AllowFileAccessFromFileURLs（）设置为 true 时，攻击者的JS代码为
         *  通过该代码可成功读取 /etc/hosts 的内容数据
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.allowFileAccessFromFileURLs = false
        }

        /**
         * 设置是否允许通过 file url 加载的 Javascript 可以访问其他的源(包括http、https等源)
         * 在Android 4.1前默认允许（setAllowFileAccessFromFileURLs（）不起作用）
         * 在Android 4.1后默认禁止
         * 当AllowFileAccessFromFileURLs（）被设置成true时，攻击者的JS代码是
         * 通过该代码可成功读取 http://www.so.com 的内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.allowUniversalAccessFromFileURLs = false
        }



        binding.webView.loadUrl(DataConstant.ASSETS_H5TEST1_PATH)
    }

}
