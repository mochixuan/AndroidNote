package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAppbar1Binding;

public class AppBarLayout1 extends BaseActivity {

    private ActivityAppbar1Binding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appbar1;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAppbar1Binding) binding;
    }

    @Override
    public void InitData() {

    }


}
