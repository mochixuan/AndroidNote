package com.wx.viewpagermaster.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wx.viewpagermaster.bean.TabBean;

import java.util.List;

/**
 * Created by wangxuan on 2018/1/25.
 */

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<TabBean> mBeans;

    public MyFragmentStatePagerAdapter(FragmentManager fm,List<TabBean> beans) {
        super(fm);
        this.mBeans = beans;
    }

    @Override
    public Fragment getItem(int position) {
        return mBeans.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mBeans.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeans.get(position).getTag();
    }

}
