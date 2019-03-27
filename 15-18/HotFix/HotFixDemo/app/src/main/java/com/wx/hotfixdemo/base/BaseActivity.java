package com.wx.hotfixdemo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity{

    public abstract int getLayoutId();

    public abstract void setDataBinding(ViewDataBinding binding);

    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding(DataBindingUtil.setContentView(this,getLayoutId()));
        initData();
    }


}
