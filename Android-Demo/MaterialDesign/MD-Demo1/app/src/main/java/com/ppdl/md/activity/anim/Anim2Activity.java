package com.ppdl.md.activity.anim;

import android.databinding.ViewDataBinding;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAnim2Binding;

public class Anim2Activity extends BaseActivity {

    private ActivityAnim2Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim2;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAnim2Binding) binding;
    }

    @Override
    public void InitData() {

    }

}
