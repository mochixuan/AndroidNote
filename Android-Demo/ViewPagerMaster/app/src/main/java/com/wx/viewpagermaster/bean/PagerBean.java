package com.wx.viewpagermaster.bean;

/**
 * Created by wangxuan on 2018/1/25.
 */

public class PagerBean {

    private String title;

    private String tag;

    private int icon;

    private int color;

    public PagerBean(String title, String tag, int icon, int color) {
        this.title = title;
        this.tag = tag;
        this.icon = icon;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
