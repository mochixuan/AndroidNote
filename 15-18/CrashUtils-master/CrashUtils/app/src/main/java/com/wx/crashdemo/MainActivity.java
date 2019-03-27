package com.wx.crashdemo;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wx.crashlibrary.manager.CrashManager;
import com.wx.crashlibrary.manager.DeviceInfoManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            Toast.makeText(MainActivity.this,"无法测试，请授权",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                int a = 1/0;
                break;
            case R.id.btn_2:
                String b = null;
                b.equals("");
                break;
            case R.id.btn_3:
                Map<Integer,Bitmap> bitmapMap = new HashMap<>();
                for (int i = 0; i <100 ;i++) {
                    bitmapMap.put(i, BitmapFactory.decodeResource(getResources(),R.mipmap.icon_2k));
                }
                break;
            case R.id.btn_4:
                try {
                    int c = 1/0;
                } catch (Exception e) {

                }
                break;
            case R.id.btn_5:
                Logger.json(DeviceInfoManager.builder(MainActivity.this).getJsonData());
                break;
            case R.id.btn_6:
                new Thread(){
                    @Override
                    public void run() {
                        int d= 1/0;
                    }
                }.start();
                break;
            case R.id.btn_8:
                CrashManager.getInstance().init().clearDefaultLog();
                break;
        }
    }

}
