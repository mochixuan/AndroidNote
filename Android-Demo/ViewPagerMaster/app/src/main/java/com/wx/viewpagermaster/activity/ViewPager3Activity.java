package com.wx.viewpagermaster.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.adapter.MyFragmentStatePagerAdapter;
import com.wx.viewpagermaster.base.BaseFragmentActivity;
import com.wx.viewpagermaster.bean.TabBean;
import com.wx.viewpagermaster.databinding.ActivityViewpager3Binding;
import com.wx.viewpagermaster.fragment.FragmentOne;

import java.util.ArrayList;
import java.util.List;

public class ViewPager3Activity extends BaseFragmentActivity {

    private ActivityViewpager3Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager3;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityViewpager3Binding) binding;
    }

    @Override
    public void initData() {
        List<TabBean> lists = new ArrayList<>();
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第1个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第2个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第3个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第4个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第5个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第6个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第7个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第8个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第9个"))," ",R.mipmap.icon1));
        lists.add(new TabBean(FragmentOne.newInstance(getBundle("第10个"))," ",R.mipmap.icon1));
        binding.viewPager3.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),lists));
    }

    private Bundle getBundle(String data) {
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
        return bundle;
    }

}
