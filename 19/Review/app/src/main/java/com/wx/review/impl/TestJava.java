package com.wx.review.impl;

import com.wx.review.data.constant.DataConstant;

public class TestJava {

    @AnnotationBean
    private static int mCurWorkDay;

    public static void test() {
        setWorkDay(DataConstant.one);
        //getWorkDay(DataConstant.eight);
    }

    public static void setWorkDay(@AnnotationBean int data) {
        mCurWorkDay = data;
    }

    @AnnotationBean
    public static int getWorkDay() {
        return mCurWorkDay;
    }

}
