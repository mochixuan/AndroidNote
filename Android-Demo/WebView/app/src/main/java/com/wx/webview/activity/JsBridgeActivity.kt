package com.wx.webview.activity

import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Build
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import com.wx.glidedemo1.base.BaseActivity
import com.wx.webview.R
import com.wx.webview.constant.DataConstant
import com.wx.webview.databinding.ActivityJsBridgeBinding
import com.wx.webview.jsinterface.JsToAndroid
import com.wx.webview.jsinterface.JsToAndroidListener
import java.net.URLDecoder

class JsBridgeActivity : BaseActivity() {

    private lateinit var binding: ActivityJsBridgeBinding
    private val TAG = this.javaClass.simpleName
    private var mJsPromptResult: JsPromptResult? = null

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

        val jsToAndroid = JsToAndroid()
        jsToAndroid.setJsToAndroidListener(object : JsToAndroidListener{
            override fun hello(msg: String) {
                runOnUiThread {
                    binding.tvDetail.text = msg
                }
            }
        })
        //Js调用Android使用javascriptInterface方法：使用简单，存在漏洞
        //参数1：Javascript对象名
        //参数2：Java对象名
        //JsToAndroid类对象映射到js的android对象
        //可以有返回值
        binding.webView.addJavascriptInterface(jsToAndroid,"android")

        binding.webView.loadUrl(DataConstant.ASSETS_H5TEST2_PATH)

        binding.btn1.setOnClickListener {
            //binding.webView.loadUrl("javascript:callJSAlert()")
            binding.webView.loadUrl("javascript:callJSAlert1('莫辞旋')")
        }
        binding.btn2.setOnClickListener {
            binding.webView.loadUrl("javascript:callJSConfirm()")
        }
        binding.btn3.setOnClickListener {
            //效率高，兼容性差要>=4.4，可以接收返回值
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                binding.webView.evaluateJavascript("javascript:getLoginData()", ValueCallback {
                    //此处为 js 返回的结果
                    binding.tvDetail.text = it
                })
            }
        }
        binding.btnCommit.setOnClickListener {
            mJsPromptResult?.confirm(binding.editText.text.toString())
        }
    }

    val mWebViewClient = object : WebViewClient() {
        //复写这个方法就可以不用改：实测
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            // 不存在方式1的漏洞，js解析复杂，js无法获取返回值
            //1. Android通过 WebViewClient 的回调方法shouldOverrideUrlLoading ()拦截 url
            //2. 解析该 url 的协议
            //3. 如果检测到是预先约定好的协议，就调用相应方法

            // 步骤2：根据协议的参数，判断是否是所需要的url
            // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
            try {
                if (url != null) {
                    val uri = Uri.parse(url)
                    if (uri.scheme.equals("jswx") && uri.authority.equals("webview")) {
                        val realUrl = URLDecoder.decode(url,"UTF-8")
                        val index = realUrl!!.indexOf("?")+1
                        val urlData = realUrl.substring(index,realUrl.length)
                        val datas = urlData.split("&")
                        if (datas != null && datas.size >= 2 ) {
                            when (datas[0].substring(datas[0].indexOf("=")+1,datas[0].length)) {
                                "Toast" -> {
                                    binding.tvDetail.text = datas[1].substring(datas[1].indexOf("=")+1,datas[1].length)
                                }
                                "Save" -> {
                                    val name = datas[1].substring(datas[1].indexOf("=")+1,datas[1].length)
                                    val password = datas[1].substring(datas[1].indexOf("=")+1,datas[1].length)
                                    binding.tvDetail.text = "name = ${name} password = ${password}"
                                }
                                else -> {
                                    showToast("非法参数")
                                }
                            }
                            return true
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    // 由于设置了弹窗检验调用结果,所以需要支持js对话框
    // WebView只是载体，内容的渲染需要使用WebViewChromeClient类去实现
    // 通过设置WebChromeClient对象处理JavaScript的对话框
    //设置响应js 的Alert()函数
    val mWebChromeClient = object : WebChromeClient() {
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            Log.d(TAG,"=================onJsAlert>>"+url+" "+message)
            AlertDialog.Builder(this@JsBridgeActivity)
                    .setTitle("来自"+url+"网页")
                    .setMessage(message)
                    .setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                        result!!.confirm()
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
                    .setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                        result!!.confirm()
                    })
                    .setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                        result!!.cancel()
                    })
                    .setCancelable(true)
                    .create()
                    .show()
            return true //调用原生，下面是调用自己的
            //return super.onJsConfirm(view, url, message, result)
        }

        override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
            mJsPromptResult = result
            return true //调用原生，下面是调用自己的
            //return super.onJsPrompt(view, url, message, defaultValue, result)
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
