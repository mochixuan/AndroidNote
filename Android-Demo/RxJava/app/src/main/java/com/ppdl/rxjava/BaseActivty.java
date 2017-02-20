package com.ppdl.rxjava;

import android.app.Activity;
import android.content.Intent;

public abstract class BaseActivty extends Activity{

    public abstract void InitView();
    public abstract void InitData();

    @Override
    protected void onResume() {
        super.onResume();
        InitView();
        InitData();
    }

    public void openActivity(Class<? extends BaseActivty> toActivity){
        Intent intent=new Intent(this,toActivity);
        startActivity(intent);
    }

    public void showToast(String data){
        ToastUtils.showToast(this,data);
    }

}
