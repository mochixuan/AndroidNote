package com.wx.ipcmaster.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangxuan on 2018/1/16.
 */

public class Book implements Parcelable{

    private String name;

    private int price;

    public Book() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Book(Parcel dest) {
        name = dest.readString();
        price = dest.readInt();
    }

    public void readFromParcel(Parcel dest) {
        name = dest.readString();
        price = dest.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };

}
