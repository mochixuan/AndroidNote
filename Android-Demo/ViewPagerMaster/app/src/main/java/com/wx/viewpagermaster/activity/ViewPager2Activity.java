package com.wx.viewpagermaster.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.adapter.MyFragmentPagerAdapter;
import com.wx.viewpagermaster.base.BaseFragmentActivity;
import com.wx.viewpagermaster.databinding.ActivityViewpager2Binding;
import com.wx.viewpagermaster.fragment.FragmentOne;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends BaseFragmentActivity {

    private ActivityViewpager2Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager2;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityViewpager2Binding) binding;
    }

    @Override
    public void initData() {
        List<Fragment> lists = new ArrayList<>();
        lists.add(FragmentOne.newInstance(getBundle("第一个")));
        lists.add(FragmentOne.newInstance(getBundle("第二个")));
        lists.add(FragmentOne.newInstance(getBundle("第三个")));
        lists.add(FragmentOne.newInstance(getBundle("第四个")));
        lists.add(FragmentOne.newInstance(getBundle("第六个")));
        lists.add(FragmentOne.newInstance(getBundle("第七个")));
        lists.add(FragmentOne.newInstance(getBundle("第八个")));
        lists.add(FragmentOne.newInstance(getBundle("第九个")));
        lists.add(FragmentOne.newInstance(getBundle("第十个")));
        binding.viewPager2.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),lists));
    }

    private Bundle getBundle(String data) {
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
        return bundle;
    }

}
