package com.wx.md.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity{

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void setDataBinding(@NonNull ViewDataBinding binding);

    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding(DataBindingUtil.setContentView(this,getLayoutId()));
        initData();
    }

    public void back(View view) {
        finish();
    }

    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    public void showToast(@NonNull String data) {
        if (data != null && !data.isEmpty()) Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }


}
