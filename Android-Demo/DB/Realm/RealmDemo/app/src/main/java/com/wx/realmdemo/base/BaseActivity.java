package com.wx.realmdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wangxuan on 2017/4/6.
 */

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

    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

}
