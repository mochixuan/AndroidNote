package com.wx.proxy.base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;

import com.wx.proxy.test1.Data;
import com.wx.proxy.test1.ImgType;

public abstract class BaseActivity extends AppCompatActivity{

    //设置为Layout资源ID
    @LayoutRes
    public abstract int getLayouId();

    //当你期望传递的是一个真实的RGB或者ARGB的颜色值而不是颜色资源ID的时候可以使用该注解来
    @ColorInt
    public int colorId;
    @ColorRes
    public int colorIds;

    //启用混淆的时候告诉编译器被注解的对象不要混淆
    @Keep
    public String data;

    //不能为空
    @NonNull
    public String mTimer;

    //大小限制
    @Size(max = 10L,min = 1L)
    public long timer;

    public interface BaseView{
        @UiThread
        void showLoading();
        @WorkerThread
        void requestData();
    }

    //基本注解使用
    public void setImgType(@ImgType int type) {

    }

    @CallSuper //继承此类的必须要调用父方法
    public void show() {

    }

    //必须要调用判断返回结果
    @CheckResult
    public boolean isConnectNet() {
        return true;
    }

    //需要获取权限才可以调用这个方法
    //至少需要其中一个权限
    @RequiresPermission(anyOf = {Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE})
    public abstract void getWorkNet();
    //需要申请全部权限
    @RequiresPermission(allOf = {Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE})
    public abstract void getWorkNets();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setImgType(Data.JPG);

        boolean isConn = isConnectNet();

    }



}
