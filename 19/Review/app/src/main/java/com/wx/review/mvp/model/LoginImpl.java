package com.wx.review.mvp.model;

public class LoginImpl implements ILogin {

    private ILoginCallBack mLoginCallBack;

    public LoginImpl(ILoginCallBack loginCallBack) {
        this.mLoginCallBack = loginCallBack;
    }

    @Override
    public void login(String name, String password) {
        //登陆
        mLoginCallBack.loginResult(true);
    }
}
