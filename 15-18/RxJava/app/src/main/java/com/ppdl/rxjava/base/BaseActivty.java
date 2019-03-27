package com.ppdl.rxjava.base;

import android.app.Activity;
import android.content.Intent;

import com.ppdl.rxjava.tool.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public abstract class BaseActivty extends RxAppCompatActivity{

    public abstract void InitView();
    public abstract void InitData();

    @Override
    protected void onResume() {
        super.onResume();
        InitView();
        InitData();
    }

    public void openActivity(Class<? extends Activity> toActivity){
        Intent intent=new Intent(this,toActivity);
        startActivity(intent);
    }

    public void showToast(String data){
        ToastUtils.showToast(this,data);
    }

}
