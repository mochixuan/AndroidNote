package com.wx.realmdemo.bean;

import io.realm.RealmObject;

public class Course extends RealmObject {

    private String course;
    private int lesson;

    public Course() {
    }

    public Course(String course, int lesson) {
        this.course = course;
        this.lesson = lesson;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }
}