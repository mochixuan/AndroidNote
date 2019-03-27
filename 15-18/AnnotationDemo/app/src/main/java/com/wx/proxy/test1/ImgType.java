package com.wx.proxy.test1;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Data.JPG,Data.PNG})
@Retention(RetentionPolicy.SOURCE)  //只在源代码中保留 一般都是用来增加代码的理解性或者帮助代码检查之类的
public @interface ImgType {

}
