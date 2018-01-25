package com.wx.retrofitmaster;

import android.Manifest;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wx.retrofitmaster.base.BaseActivity;
import com.wx.retrofitmaster.constant.DataConstants;
import com.wx.retrofitmaster.databinding.ActivityMainBinding;
import com.wx.retrofitmaster.network.juhe.JuHeManager;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;


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

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().getWeChatSelection1(0);
                JuHeManager.getInstance().getWeChatSelection2(1);
                JuHeManager.getInstance().getWeChatSelection3(2);
                JuHeManager.getInstance().getWeChatSelection4(1,1,"json","97ccfd2896c2fe7d5662510254995d63",3);
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().getWeChatSelection6(1,1,"json","97ccfd2896c2fe7d5662510254995d63",0);
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().downBaiDuImg7(0,binding.ivView);
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().uploadFile8(
                        "989e15046a27d0577d6ab66d3f81a5ca",
                        "a48e6df3c0afe2cd3fb7c1841041e4aa",
                        "2",
                        "w123",
                        "测试下上传功能",
                        DataConstants.DANCANFAN_PATH,
                        0
                );
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().getWeChatSelection9(0);
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JuHeManager.getInstance().getWeChatSelection10(0);
                JuHeManager.getInstance().getWeChatSelection5(1);
                JuHeManager.getInstance().getWeChatSelection11(2);
                JuHeManager.getInstance().getWeChatSelection12(3);
                JuHeManager.getInstance().getWeChatSelection13(4);
                JuHeManager.getInstance().getWeChatSelection14(5);
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
