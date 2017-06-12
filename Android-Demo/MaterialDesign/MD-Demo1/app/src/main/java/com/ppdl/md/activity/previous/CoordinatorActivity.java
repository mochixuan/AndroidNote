package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;

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
