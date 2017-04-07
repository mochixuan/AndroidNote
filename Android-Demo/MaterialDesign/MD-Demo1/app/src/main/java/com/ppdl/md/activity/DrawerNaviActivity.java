package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityDrawerBinding;

public class DrawerNaviActivity extends BaseActivity {

    private ActivityDrawerBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityDrawerBinding) binding;
    }

    @Override
    public void InitData() {

    }

}
