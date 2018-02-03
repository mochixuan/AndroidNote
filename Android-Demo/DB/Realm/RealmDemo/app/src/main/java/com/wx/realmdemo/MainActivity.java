package com.wx.realmdemo;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.realmdemo.activity.Main2Activity;
import com.wx.realmdemo.activity.Main3Activity;
import com.wx.realmdemo.activity.Main4Activity;
import com.wx.realmdemo.base.BaseActivity;
import com.wx.realmdemo.databinding.ActivityMainBinding;
import com.wx.realmdemo.manager.RealmManager;

public class MainActivity extends BaseActivity implements View.OnClickListener{

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
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                openActivity(Main2Activity.class);
                break;
            case R.id.btn_2:
                openActivity(Main3Activity.class);
                break;
            case R.id.btn_3:
                openActivity(Main4Activity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmManager.getInstance().release();
    }

}
