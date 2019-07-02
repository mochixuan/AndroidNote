package com.wx.review.mvp.base;

import android.databinding.ViewDataBinding;

public interface IBaseView {

    void showLoading();

    void hideLoading();

    int getLayoutId();

    void setDataBinding(ViewDataBinding binding);

    void initData();

    boolean isDestroy();

    IBasePresenter getPresenter();

}
