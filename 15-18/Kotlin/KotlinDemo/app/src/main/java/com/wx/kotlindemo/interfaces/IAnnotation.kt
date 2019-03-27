package com.wx.kotlindemo.interfaces

import android.support.annotation.IdRes
import android.support.annotation.IntDef
import android.support.annotation.LayoutRes
import com.wx.kotlindemo.constant.DataConstant
import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target

/**
 * Created by wangxuan on 2018/2/1.
 */

@IntDef(DataConstant.JPG, DataConstant.PNG)
@Retention(RetentionPolicy.SOURCE)  //只在源代码中保留 一般都是用来增加代码的理解性或者帮助代码检查之类的
annotation class ImgType

@IntDef(DataConstant.JPG, DataConstant.PNG)
@Retention(RetentionPolicy.SOURCE)  //只在源代码中保留 一般都是用来增加代码的理解性或者帮助代码检查之类的
annotation class ImgType1(val type: Long)

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class BindView(@IdRes val value: Int)

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class LayoutId(@LayoutRes val value: Int)