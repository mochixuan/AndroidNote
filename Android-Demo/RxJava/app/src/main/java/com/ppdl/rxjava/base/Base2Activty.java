package com.ppdl.rxjava.base;

import android.app.Activity;
import android.content.Intent;

import com.ppdl.rxjava.tool.ToastUtils;

public abstract class Base2Activty extends com.trello.rxlifecycle2.components.support.RxAppCompatActivity{

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
