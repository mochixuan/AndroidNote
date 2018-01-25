package com.wx.viewpagermaster.bean;

import android.support.v4.app.Fragment;

/**
 * Created by wangxuan on 2018/1/25.
 */

public class TabBean {

    private Fragment fragment;

    private String tag;

    private int icon;

    public TabBean(Fragment fragment, String tag, int icon) {
        this.fragment = fragment;
        this.tag = tag;
        this.icon = icon;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
