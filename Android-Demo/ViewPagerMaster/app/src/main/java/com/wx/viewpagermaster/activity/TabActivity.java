package com.wx.viewpagermaster.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.adapter.MyFragmentStatePagerAdapter;
import com.wx.viewpagermaster.base.BaseFragmentActivity;
import com.wx.viewpagermaster.bean.TabBean;
import com.wx.viewpagermaster.databinding.ActivityTabBinding;
import com.wx.viewpagermaster.fragment.FragmentOne;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends BaseFragmentActivity {

    private ActivityTabBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityTabBinding) binding;
    }

    @Override
    public void initData() {
        List<TabBean> lists = new ArrayList<>();
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("蝴蝶"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("昆虫"))," ",R.mipmap.icon2));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("鹦鹉"))," ",R.mipmap.icon3));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("奶牛"))," ",R.mipmap.icon4));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("鳄鱼"))," ",R.mipmap.icon5));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("鲫鱼"))," ",R.mipmap.icon6));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("哈士奇"))," ",R.mipmap.icon7));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("狮子"))," ",R.mipmap.icon8));
        binding.viewPager4.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),lists));

        binding.tabLayout.setupWithViewPager(binding.viewPager4);
    }

    private Bundle getBundle(String data) {
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
        return bundle;
    }

}
