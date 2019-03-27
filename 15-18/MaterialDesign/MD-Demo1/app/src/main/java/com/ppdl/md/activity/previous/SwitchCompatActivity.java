package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
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
