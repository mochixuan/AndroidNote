package com.wx.realmdemo.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject{

    private String name;

    @PrimaryKey
    private long num;

    private RealmList<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public RealmList<Course> getCourses() {
        return courses;
    }

    public void setCourses(RealmList<Course> courses) {
        this.courses = courses;
    }

}
