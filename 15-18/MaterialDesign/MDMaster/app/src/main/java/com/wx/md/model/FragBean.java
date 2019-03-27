package com.wx.md.model;

import android.support.annotation.IdRes;

import com.wx.md.base.BaseFragment;

public class FragBean {

    private String tag;

    @IdRes
    private int icon;

    private BaseFragment fragment;

    public FragBean(String tag, int icon, BaseFragment fragment) {
        this.tag = tag;
        this.icon = icon;
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

    public BaseFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }
}
