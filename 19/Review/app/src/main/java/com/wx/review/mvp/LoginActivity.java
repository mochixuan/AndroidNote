package com.wx.review.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.wx.review.R;
import com.wx.review.databinding.ActivityLoginBinding;
import com.wx.review.mvp.base.BaseActivity;
import com.wx.review.mvp.base.IBasePresenter;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    private LoginPresenter mLoginPresenter;
    private ActivityLoginBinding binding;


    @Override
    public void onLoginState(boolean isSuccess) {
        binding.tvState.setText("登陆:"+(isSuccess ? "成功": "失败"));
        
    }

    interface Test {
        String a = "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityLoginBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.onLogin("mochixuan","123456");
            }
        });
    }

    @Override
    public IBasePresenter getPresenter() {
        if (mLoginPresenter == null) {
            mLoginPresenter = new LoginPresenter(this);
        }
        return mLoginPresenter;
    }
}
