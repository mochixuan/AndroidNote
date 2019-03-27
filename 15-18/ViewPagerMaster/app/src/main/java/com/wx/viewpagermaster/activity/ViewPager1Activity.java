package com.wx.viewpagermaster.activity;

import android.databinding.ViewDataBinding;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.adapter.MyPagerAdapter;
import com.wx.viewpagermaster.base.BaseActivity;
import com.wx.viewpagermaster.bean.PagerBean;
import com.wx.viewpagermaster.databinding.ActivityViewpage1Binding;

import java.util.ArrayList;
import java.util.List;

public class ViewPager1Activity extends BaseActivity {

    private ActivityViewpage1Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpage1;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityViewpage1Binding) binding;
    }

    @Override
    public void initData() {
        List<PagerBean> pagerBeans = new ArrayList<>();
        pagerBeans.add(new PagerBean("蝴蝶","蝴蝶",R.mipmap.icon1,getResources().getColor(R.color.color1)));
        pagerBeans.add(new PagerBean("昆虫","昆虫",R.mipmap.icon2,getResources().getColor(R.color.color2)));
        pagerBeans.add(new PagerBean("鹦鹉","鹦鹉",R.mipmap.icon3,getResources().getColor(R.color.color3)));
        pagerBeans.add(new PagerBean("奶牛","奶牛",R.mipmap.icon4,getResources().getColor(R.color.color4)));
        MyPagerAdapter adapter = new MyPagerAdapter(pagerBeans,this);
        binding.viewPager1.setAdapter(adapter);
    }

}
