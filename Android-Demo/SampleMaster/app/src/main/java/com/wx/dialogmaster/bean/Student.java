package com.wx.dialogmaster.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wangxuan on 2018/1/17.
 */

public class Student implements Parcelable {

    private String num;

    private List<Course> courses;

    private User user;

    public Student() {

    }

    protected Student(Parcel in) {
        num = in.readString();
        courses = in.readArrayList(Course.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(num);
        parcel.writeList(courses);
        parcel.writeParcelable(user, i);
    }

}
