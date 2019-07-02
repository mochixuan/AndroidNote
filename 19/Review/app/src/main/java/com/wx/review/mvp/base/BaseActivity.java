package com.wx.review.mvp.base;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public abstract class BaseActivity extends Activity implements IBaseView{

    private boolean isDestroy = false;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding(DataBindingUtil.setContentView(this,getLayoutId()));
        initData();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    protected void openActivity(Activity activity) {

    }

    protected void showToast(String data) {

    }

    protected void showToast(@StringRes int data) {

    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

}
