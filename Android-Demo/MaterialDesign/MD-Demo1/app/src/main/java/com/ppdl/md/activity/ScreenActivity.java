package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.activity.screen.ScreenOneActivity;
import com.ppdl.md.activity.screen.ScreenTwoActivity;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityScreenBinding;

public class ScreenActivity extends BaseActivity implements View.OnClickListener{

    private ActivityScreenBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_screen;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityScreenBinding) binding;
    }

    @Override
    public void InitData() {
        binding.btnScreen1.setOnClickListener(this);
        binding.btnScreen2.setOnClickListener(this);
        binding.btnScreen3.setOnClickListener(this);
        binding.btnScreen4.setOnClickListener(this);
        binding.btnScreen5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_screen_1:
                openActitivity(ScreenOneActivity.class);
                break;
            case R.id.btn_screen_2:
                openActitivity(ScreenTwoActivity.class);
                break;
            case R.id.btn_screen_3:
                break;
            case R.id.btn_screen_4:
                break;
            case R.id.btn_screen_5:
                break;
        }
    }

}
