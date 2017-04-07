package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAppbarBinding;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class AppBarActivity extends BaseActivity {

    private ActivityAppbarBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appbar;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAppbarBinding) binding;
    }

    @Override
    public void InitData() {
        binding.toolBar.setTitle("这是个标题");
        binding.toolBar.setSubtitle("这是子标题");
        binding.toolBar.setLogo(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(binding.toolBar);

    }

}
