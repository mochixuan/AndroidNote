package com.wx.zxingdemo;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.zxingdemo.activity.CreateQrActivity;
import com.wx.zxingdemo.activity.ScanActivity;
import com.wx.zxingdemo.base.BaseActivity;
import com.wx.zxingdemo.databinding.ActivityMainBinding;

//项目中要使用，写个demo熟悉一下
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnScan.setOnClickListener(this);
        binding.btnGenerate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generate:
                openActivity(CreateQrActivity.class);
                break;
            case R.id.btn_scan:
                openActivity(ScanActivity.class);
                break;
        }
    }
}
