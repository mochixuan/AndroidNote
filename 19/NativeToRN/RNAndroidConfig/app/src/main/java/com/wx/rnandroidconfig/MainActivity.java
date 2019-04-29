package com.wx.rnandroidconfig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wx.rnandroidconfig.activity.OneActivity;
import com.wx.rnandroidconfig.activity.ThreeActivity;
import com.wx.rnandroidconfig.activity.TwoActivity;
import com.wx.rnandroidconfig.constant.BaseConstant;
import com.wx.rnandroidconfig.preloadreact.ReactNativePreLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ReactNativePreLoader.preLoad(MainActivity.this,BaseConstant.RN_MC_NAME_TWO);
    }

    private void initBtn() {
        findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(OneActivity.class);
            }
        });

        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //预加载
                Log.d(this.getClass().getSimpleName(),"==============预加载>>>");
                openActivity(TwoActivity.class);
            }
        });

        findViewById(R.id.btn_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ThreeActivity.class);
            }
        });
    }

    private void openActivity(Class activityClass) {
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
    }

}
