package com.ppdl.md.activity.screen;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityJumpOneBinding;

public class JumpOneActivity extends BaseActivity {

    private ActivityJumpOneBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jump_one;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityJumpOneBinding) binding;
    }

    @Override
    public void InitData() {
    }

}
