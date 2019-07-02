package com.wx.review.impl;

import android.support.annotation.IntDef;
import com.wx.review.data.constant.DataConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    DataConstant.one,
    DataConstant.two,
    DataConstant.three,
    DataConstant.four,
    DataConstant.five,
    DataConstant.six,
    DataConstant.seven
})
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationBean {

}
