package com.wx.daggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    protected TextView mTvText;

    @BindString(R.string.app_name)
    protected String mAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);

        mTvText.setText(mAppName);
    }

}
