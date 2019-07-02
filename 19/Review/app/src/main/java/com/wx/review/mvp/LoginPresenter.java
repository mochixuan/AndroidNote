package com.wx.review.mvp;

import com.wx.review.mvp.model.ILoginCallBack;
import com.wx.review.mvp.model.LoginImpl;

public class LoginPresenter implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView mLoginView;
    private LoginImpl mLoginImpl;
    private ILoginCallBack mLoginCallBack;

    public LoginPresenter(ILoginContract.ILoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void onLogin(String name, String password) {
        mLoginImpl.login(name,password);
    }

    @Override
    public void onStart() {
        mLoginCallBack = new ILoginCallBack() {
            @Override
            public void loginResult(boolean isSuccess) {
                if (!mLoginView.isDestroy()) {
                    mLoginView.onLoginState(isSuccess);
                }
            }
        };

        mLoginImpl = new LoginImpl(mLoginCallBack);
    }

    @Override
    public void onDestroy() {

    }
}
