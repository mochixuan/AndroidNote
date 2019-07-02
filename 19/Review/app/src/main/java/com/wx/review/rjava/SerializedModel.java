package com.wx.review.rjava;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedModel implements Parcelable{

    static class Person implements Parcelable{

        protected Person(Parcel in) {
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }
    }

    private String name;
    private String num;
    private int age;
    private List<String> courses;
    private List<Person> persons;
    private transient String temp1; //临时数据
    private static String temp2; //临时数据

    protected SerializedModel(Parcel in) {
        name = in.readString();
        num = in.readString();
        age = in.readInt();
        courses = in.createStringArrayList();
        persons = in.createTypedArrayList(Person.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(num);
        dest.writeInt(age);
        dest.writeStringList(courses);
        dest.writeTypedList(persons);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SerializedModel> CREATOR = new Creator<SerializedModel>() {
        @Override
        public SerializedModel createFromParcel(Parcel in) {
            return new SerializedModel(in);
        }

        @Override
        public SerializedModel[] newArray(int size) {
            return new SerializedModel[size];
        }
    };
}
