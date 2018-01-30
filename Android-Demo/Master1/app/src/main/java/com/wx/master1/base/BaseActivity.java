package com.wx.master1.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by wangxuan on 2018/1/16.
 */

public abstract class BaseActivity extends Activity{

    public abstract int getLayoutId();

    public abstract void setDataBinding(ViewDataBinding binding);

    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding(DataBindingUtil.setContentView(this,getLayoutId()));
        initData();
    }

    public void showToast(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

    public void openActivity(Class<? extends Activity> aclass) {
        startActivity(new Intent(this,aclass));
    }

}
