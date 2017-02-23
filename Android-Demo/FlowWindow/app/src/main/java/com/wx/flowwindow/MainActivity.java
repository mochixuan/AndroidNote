package com.wx.flowwindow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wx.flowwindow.Tools.TrafficFloatWindowService;
import com.wx.flowwindow.Tools.TrafficInfo;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,TrafficFloatWindowService.class)) ;
                mHandler.sendEmptyMessage(MSG_BASE);
            }
        });
        textView = (TextView) findViewById(R.id.tv_speed);
    }

    private static final int MSG_BASE = 1;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mHandler.removeMessages(MSG_BASE);
            mHandler.sendEmptyMessageDelayed(MSG_BASE,1000);
            textView.setText(TrafficInfo.getNetSpeed2String());
        }
    };

}
