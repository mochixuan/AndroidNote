package com.ppdl.md.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity{

    private ViewDataBinding binding;

    public abstract int getLayoutId();
    public abstract void InitBing(ViewDataBinding binding);
    public abstract void InitData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        InitBing(binding);
        InitData();
    }

    public void openActitivity(Class<? extends BaseActivity> otherActivity){
        Intent intent = new Intent(this,otherActivity);
        startActivity(intent);
    }

}
