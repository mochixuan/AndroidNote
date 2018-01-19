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
    private static final String BAIDU_URL = "https://www.baidu.com/";
    private static final String IMG_URL = "http://n.sinaimg.cn/news/transform/w1000h500/20180115/Vdho-fyqrewi3455402.jpg";
    private static final String FILE_QQ_URL = "http://imtt.dd.qq.com/16891/33757913E2C5D213D1A980EE55FF19C0.apk?fsname=com.tencent.qqmusic_7.9.5.16_763.apk&csr=1bbd";
    private static final String FILE_UPLOAD_URL1 = "https://api.github.com/markdown/raw";
    private static final String FILE_UPLOAD_URL2 = "https://api.github.com/markdown/raw";

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
                OkSimaple1.getInstance().downFile(FILE_QQ_URL, Environment.getExternalStorageDirectory()+"/okttpsimaple/qq.apk",1);
            }
        });
        binding.btnUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("image",new File(Environment.getExternalStorageDirectory()+"/okttpsimaple/test.png"));
                OkSimaple1.getInstance().uploadFile(FILE_UPLOAD_URL1,map,0);
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
