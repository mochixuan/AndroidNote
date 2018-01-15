package com.wx.ipcmaster;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.ipcmaster.activity.AidlActivity;
import com.wx.ipcmaster.activity.MessengerActivity;
import com.wx.ipcmaster.activity.base.BaseActivity;
import com.wx.ipcmaster.databinding.ActivityMainBinding;

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
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MessengerActivity.class);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AidlActivity.class);
            }
        });

    }

}
