package com.wx.md.model;

public class DataBean {

    private int icon;

    private String data;

    public DataBean(int icon, String data) {
        this.icon = icon;
        this.data = data;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
