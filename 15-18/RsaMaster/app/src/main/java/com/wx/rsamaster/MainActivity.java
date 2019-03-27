package com.wx.rsamaster;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wx.rsamaster.databinding.ActivityMainBinding;
import com.wx.rsamaster.secret.tool.AESTool;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {

        binding.btnEncry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvEncry.setText(AESTool.getInstance().encrypt(AESTool.KEY,binding.edDetail.getText().toString()));
            }
        });
        binding.btnDecry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvDecry.setText(AESTool.getInstance().decrypt(AESTool.KEY,binding.tvEncry.getText().toString()));
            }
        });
    }

}
