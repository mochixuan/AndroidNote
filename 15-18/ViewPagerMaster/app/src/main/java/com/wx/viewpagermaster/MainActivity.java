package com.wx.viewpagermaster;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.viewpagermaster.activity.TabActivity;
import com.wx.viewpagermaster.activity.ViewPager1Activity;
import com.wx.viewpagermaster.activity.ViewPager2Activity;
import com.wx.viewpagermaster.activity.ViewPager3Activity;
import com.wx.viewpagermaster.base.BaseActivity;
import com.wx.viewpagermaster.databinding.ActivityMainBinding;

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
                openActivity(ViewPager1Activity.class);
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ViewPager2Activity.class);
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ViewPager3Activity.class);
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(TabActivity.class);
            }
        });
    }

}
