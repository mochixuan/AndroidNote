package com.wx.master1;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.master1.activity.JsonActivity;
import com.wx.master1.base.BaseActivity;
import com.wx.master1.databinding.ActivityMainBinding;

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
        binding.btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(JsonActivity.class);
            }
        });
    }



}
