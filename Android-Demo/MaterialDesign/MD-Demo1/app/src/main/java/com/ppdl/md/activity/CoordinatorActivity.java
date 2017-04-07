package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityCoordinatorBinding;

public class CoordinatorActivity extends BaseActivity {

    private ActivityCoordinatorBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding= (ActivityCoordinatorBinding) binding;
    }

    @Override
    public void InitData() {

    }

}
