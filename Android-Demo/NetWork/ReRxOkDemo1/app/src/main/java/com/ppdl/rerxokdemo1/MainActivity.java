package com.ppdl.rerxokdemo1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rerxokdemo1.NetRequest.RequestUtils;

public class MainActivity extends Activity {

    private TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
        InitData();
    }

    private void InitView() {
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    private void InitData() {
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestUtils.getToken();
            }
        });
    }

}
