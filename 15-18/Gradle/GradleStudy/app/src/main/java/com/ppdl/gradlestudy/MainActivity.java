package com.ppdl.gradlestudy;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ppdl.Constant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv_descr);
        String channelName = "无";
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
            channelName = info.metaData.getString("CHANNEL_NAME");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        textView.setText("渠道名: "+channelName+"\n常量来自"+ Constant.NAME+"\n包名: "+getPackageName());

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }
}
