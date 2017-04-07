package com.wx.realmdemo.bean;

import io.realm.RealmObject;

/**
 * Created by wangxuan on 2017/4/6.
 */

public class User extends RealmObject{

    private int age;

    private String name;

    private boolean isMale;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
