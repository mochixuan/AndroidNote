package com.wx.webview.activity

import android.databinding.ViewDataBinding
import android.graphics.Bitmap
import android.net.http.SslError
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.constant.DataConstant
import com.wx.webview.databinding.ActivityBasicUsageBinding

class BasicUsageActivity : BaseActivity() {

    private lateinit var binding: ActivityBasicUsageBinding
    private val TAG = this.javaClass.simpleName

    override val layoutId: Int
        get() = R.layout.activity_basic_usage

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityBasicUsageBinding
    }

    override fun initData() {

        binding.webView.webViewClient = mWebViewClient
        binding.webView.webChromeClient = mWebChromeClient
        binding.webView.loadUrl(DataConstant.GITHUB_URL)


        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        //设置缓存模式
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
    }

    private val mWebViewClient = object : WebViewClient() {
        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url) //加载资源 图片什么可以拦截
            Log.d(TAG,"=================onLoadResource>>"+url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            //本地WebView加载不许跳浏览器
            view!!.loadUrl(url)
            Log.d(TAG,"=================shouldOverrideUrlLoading Old>>"+url)
            return true
        }

        //不会被调用
        override fun shouldOverrideUrlLoading(webView: WebView?, request: WebResourceRequest?): Boolean {
            Log.d(TAG,"=================shouldOverrideUrlLoading New>>"+request!!.toString())
            return super.shouldOverrideUrlLoading(webView, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.llLoading.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.llLoading.visibility = View.GONE
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            binding.llLoading.visibility = View.GONE
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            //handler?.proceed()
        }

    }

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            Log.d(TAG,"=================>>"+newProgress)
            binding.circleProgress.setProgress(newProgress)
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            binding.tvTitle.text = title!!
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun releaseWebView() {
        binding.webView.loadDataWithBaseURL(null,"","text/html","UTF-8",null)
        binding.webView.clearHistory() //对缓存无影响
        binding.webView.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseWebView()
    }

}
