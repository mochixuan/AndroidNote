package com.wx.okhttp;

import android.Manifest;
import android.databinding.ViewDataBinding;
import android.os.Environment;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wx.okhttp.base.BaseActivity;
import com.wx.okhttp.databinding.ActivityMainBinding;
import com.wx.okhttp.tool.OkSimaple1;

import java.io.File;
import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private static final String WEIXIN_BASE_URL = "http://v.juhe.cn/weixin/query";
    private static final String WEIXIN_URL = "http://v.juhe.cn/weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63";
    private static final String BAIDU_URL = "https://www.baidu.com/";
    private static final String IMG_URL = "http://n.sinaimg.cn/news/transform/w1000h500/20180115/Vdho-fyqrewi3455402.jpg";
    private static final String FILE_QQ_URL = "http://imtt.dd.qq.com/16891/33757913E2C5D213D1A980EE55FF19C0.apk?fsname=com.tencent.qqmusic_7.9.5.16_763.apk&csr=1bbd";
    private static final String FILE_FRIEDRICE_URL = "http://imtt.dd.qq.com/16891/DCF24BFF065838E75D51F6645595B56C.apk?fsname=com.wx.cookbook_3.0_3.apk&csr=1bbd";
    private static final String FILE_UPLOAD_URL1 = "https://qiniu-storage.pgyer.com/apiv1/app/upload"; //蒲公英接口
    private static final String ZHIHU_LOGIN_URL = "https://www.zhihu.com/login/phone_num";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        initPermission();

        final HashMap<String,String> weixinParams = new HashMap<>();
        weixinParams.put("pno","1");
        weixinParams.put("ps","10");
        weixinParams.put("dtype","json");
        weixinParams.put("key","97ccfd2896c2fe7d5662510254995d63");

        binding.btnGet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkSimaple1.getInstance().get(WEIXIN_BASE_URL,weixinParams,0);
                OkSimaple1.getInstance().get(BAIDU_URL,null,1);
            }
        });
        binding.btnPost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkSimaple1.getInstance().postJson(WEIXIN_BASE_URL,weixinParams,0);
                OkSimaple1.getInstance().postForm(WEIXIN_BASE_URL,weixinParams,1);
            }
        });
        binding.btnDownfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OkSimaple1.getInstance().downFile(IMG_URL, Environment.getExternalStorageDirectory()+"/okttpsimaple/test.png",0);
                //OkSimaple1.getInstance().downFile(FILE_QQ_URL, Environment.getExternalStorageDirectory()+"/okttpsimaple/qq.apk",1);
                OkSimaple1.getInstance().downFile(FILE_FRIEDRICE_URL, Environment.getExternalStorageDirectory()+"/okttpsimaple/friedrice.apk",2);
            }
        });
        binding.btnUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("uKey","989e15046a27d0577d6ab66d3f81a5ca");     //(必填)用户Key
                map.put("_api_key","a48e6df3c0afe2cd3fb7c1841041e4aa"); //(必填)APIKey
                map.put("file",new File(Environment.getExternalStorageDirectory()+"/okttpsimaple/friedrice.apk")); //(必填) 需要上传的ipa或者apk文件
                map.put("installType","2"); //(选填)应用安装方式，值为(1,2,3)。1：公开，2：密码安装，3：邀请安装。默认为1公开
                map.put("password","w123"); //(选填) 设置App安装密码，如果不想设置密码，请传空字符串，或不传。
                map.put("updateDescription","测试下上传功能"); //(选填) 版本更新描述，请传空字符串，或不传。
                OkSimaple1.getInstance().uploadFile(FILE_UPLOAD_URL1,map,0);
            }
        });
        binding.btnUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("uKey","989e15046a27d0577d6ab66d3f81a5ca");     //(必填)用户Key
                map.put("_api_key","a48e6df3c0afe2cd3fb7c1841041e4aa"); //(必填)APIKey
                map.put("file",new File(Environment.getExternalStorageDirectory()+"/okttpsimaple/friedrice.apk")); //(必填) 需要上传的ipa或者apk文件
                map.put("installType","2"); //(选填)应用安装方式，值为(1,2,3)。1：公开，2：密码安装，3：邀请安装。默认为1公开
                map.put("password","w123"); //(选填) 设置App安装密码，如果不想设置密码，请传空字符串，或不传。
                map.put("updateDescription","测试下上传功能"); //(选填) 版本更新描述，请传空字符串，或不传。
                OkSimaple1.getInstance().uploadProgressFile(FILE_UPLOAD_URL1,map,0);
            }
        });
        binding.btnCache1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkSimaple1.getInstance().cacheRequest(WEIXIN_URL,MainActivity.this,0);
            }
        });
        binding.btnCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //模拟知乎登入
                HashMap<String,String> params = new HashMap<>();
                params.put("_xsrf","bf284aba4cc706ebfc5ebcba1c4f97fc");
                params.put("password","w123456789");
                params.put("phone_num","18296154769");
                params.put("remember_me","true");
                params.put("captcha_type","cn");
                OkSimaple1.getInstance().cookie(ZHIHU_LOGIN_URL,MainActivity.this,params,0);
            }
        });
        binding.btnInterceptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkSimaple1.getInstance().interceptor(WEIXIN_URL,MainActivity.this,0);
            }
        });

    }

    private void initPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) finish();
                    }
                });
    }

}
