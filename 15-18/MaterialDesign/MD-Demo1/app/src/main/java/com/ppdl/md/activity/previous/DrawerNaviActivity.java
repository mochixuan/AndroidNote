package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;

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
