package com.wx.review.mvp;

import com.wx.review.mvp.base.IBasePresenter;
import com.wx.review.mvp.base.IBaseView;

public interface ILoginContract {

    interface ILoginView extends IBaseView{
        void onLoginState(boolean isSuccess);
    }

    interface ILoginPresenter extends IBasePresenter{
        void onLogin(String name,String password);
    }

}
