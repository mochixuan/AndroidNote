package com.wx.webview.activity

import android.annotation.TargetApi
import android.databinding.ViewDataBinding
import android.os.Build
import android.webkit.*
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.constant.DataConstant
import com.wx.webview.databinding.ActivityCacheMechainBinding
import java.io.InputStream

class CacheMechainActivity : BaseActivity() {

    private lateinit var binding: ActivityCacheMechainBinding

    override val layoutId: Int
        get() = R.layout.activity_cache_mechain

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityCacheMechainBinding
    }

    override fun initData() {
        binding.webView.webViewClient = mWebViewClient
        binding.webView.webChromeClient = mWebChromeClient

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true

        binding.webView.loadUrl(DataConstant.ASSETS_H5TEST3_PATH)
    }

    private val mWebViewClient = object : WebViewClient() {

        // API 21 以下用shouldInterceptRequest(WebView view, String url)
        // API 21 以上用shouldInterceptRequest(WebView view, WebResourceRequest request)

        override fun shouldInterceptRequest(view: WebView?, url: String): WebResourceResponse {

            //步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
            if (url.contains("logo")) {
                //假设网页里该图片资源的地址为：http://abc.com/imgage/logo.gif
                //图片的资源文件名为:logo.gif
                var ism: InputStream? = null
                try {
                    ism = this@CacheMechainActivity.assets.open("images/abc.png")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (ism != null) {
                    val response = WebResourceResponse("image/png","utf-8",ism)
                    // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                    // 参数2：编码类型
                    // 参数3：存放着替换资源的输入流（上面创建的那个）
                    return response
                }
            }

            return super.shouldInterceptRequest(view, url)
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse {
            //步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
            if (request.url.toString().contains("logo")) {
                //假设网页里该图片资源的地址为：http://abc.com/imgage/logo.gif
                //图片的资源文件名为:logo.gif
                var ism: InputStream? = null
                try {
                    ism = this@CacheMechainActivity.assets.open("images/abc.png")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (ism != null) {
                    val response = WebResourceResponse("image/png","utf-8",ism)
                    // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                    // 参数2：编码类型
                    // 参数3：存放着替换资源的输入流（上面创建的那个）
                    return response
                }
            }
            return super.shouldInterceptRequest(view, request)
        }

    }

    private val mWebChromeClient = object : WebChromeClient() {

    }

}
