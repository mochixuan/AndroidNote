package com.wx.webview.activity

import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.KeyEvent
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.constant.DataConstant
import com.wx.webview.databinding.ActivityJsBridgeBinding

class JsBridgeActivity : BaseActivity() {

    private lateinit var binding: ActivityJsBridgeBinding
    private val TAG = this.javaClass.simpleName

    override val layoutId: Int
        get() = R.layout.activity_js_bridge

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityJsBridgeBinding
    }

    override fun initData() {

        binding.webView.webViewClient = mWebViewClient
        binding.webView.webChromeClient = mWebChromeClient //WebView只是一个承载体，各种内容的渲染需要使用webviewChromClient去实现

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true //设置允许JS弹窗

        binding.webView.loadUrl(DataConstant.ASSETS_H5TEST2_PATH)

        binding.btn1.setOnClickListener {
            binding.webView.loadUrl("javascript:callJSAlert()")
        }
        binding.btn2.setOnClickListener {
            binding.webView.loadUrl("javascript:callJSConfirm()")
        }
    }

    val mWebViewClient = object : WebViewClient() {
        //复写这个方法就可以不用改：实测
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    // 由于设置了弹窗检验调用结果,所以需要支持js对话框
    // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
    // 通过设置WebChromeClient对象处理JavaScript的对话框
    //设置响应js 的Alert()函数
    val mWebChromeClient = object : WebChromeClient() {
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            Log.d(TAG,"=================onJsAlert>>"+url+" "+message+"  ")
            AlertDialog.Builder(this@JsBridgeActivity)
                    .setTitle("来自"+url+"网页")
                    .setMessage(message)
                    .setPositiveButton("确定",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            result!!.confirm()
                        }
                    })
                    .setCancelable(true)
                    .create()
                    .show()
            return true //调用原生，下面是调用自己的
            //return super.onJsAlert(view, url, message, result)
        }

        override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            AlertDialog.Builder(this@JsBridgeActivity)
                    .setTitle("来自"+url+"网页")
                    .setMessage(message)
                    .setPositiveButton("确定",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            result!!.confirm()
                        }
                    })
                    .setNegativeButton("取消",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            result!!.cancel()
                        }
                    })
                    .setCancelable(true)
                    .create()
                    .show()
            return true //调用原生，下面是调用自己的
            //return super.onJsConfirm(view, url, message, result)
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
