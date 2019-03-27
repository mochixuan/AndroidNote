package com.wx.hotfixdemo;

import android.Manifest;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.wx.hotfixdemo.base.BaseActivity;
import com.wx.hotfixdemo.databinding.ActivityMainBinding;

import rx.functions.Action1;

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
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SophixManager.getInstance().queryAndLoadNewPatch();
                //Toast.makeText(MainActivity.this, "测试版本:"+ VersionTool.getVersionName(MainActivity.this),Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "升级版本:"+VersionTool.getVersionName(MainActivity.this),Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "第三个HotFix",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initPermission() {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_WIFI_STATE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (!aBoolean) {
                            Toast.makeText(MainActivity.this,"请添加权限，否则后续测试可能失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
