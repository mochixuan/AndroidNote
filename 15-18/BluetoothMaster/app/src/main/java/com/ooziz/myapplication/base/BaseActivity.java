package com.ooziz.myapplication.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

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

    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    public void showToast(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

}
