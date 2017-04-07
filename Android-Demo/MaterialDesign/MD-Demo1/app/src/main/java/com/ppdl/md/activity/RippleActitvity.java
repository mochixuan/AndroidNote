package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityRippleBinding;

public class RippleActitvity extends BaseActivity {

    private ActivityRippleBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ripple;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityRippleBinding) binding;
    }

    @Override
    public void InitData() {

    }


}
