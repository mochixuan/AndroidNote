package com.wx.md.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wx.md.model.FragBean;

import java.util.List;

public class MiPageAdapter extends FragmentStatePagerAdapter{

    private List<FragBean> fragments;

    public MiPageAdapter(FragmentManager fm, List<FragBean> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTag();
    }

}
