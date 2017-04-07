package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityCoordinatorBinding;
import com.ppdl.md.databinding.ActivitySwitchBinding;

public class SwitchCompatActivity extends BaseActivity {

    private ActivitySwitchBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_switch;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivitySwitchBinding) binding;
    }

    @Override
    public void InitData() {

    }

}
