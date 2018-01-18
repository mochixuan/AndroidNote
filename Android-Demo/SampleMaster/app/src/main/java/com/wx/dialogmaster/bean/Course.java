package com.wx.dialogmaster.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangxuan on 2018/1/17.
 */

public class Course implements Parcelable{

    private String name;

    private String date;

    public Course(String name, String date) {
        this.name = name;
        this.date = date;
    }

    protected Course(Parcel in) {
        name = in.readString();
        date = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(date);
    }
}
