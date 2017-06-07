package com.wx.proxy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wx.proxy.R;
import com.wx.proxy.test1.Data;
import com.wx.proxy.test1.ImgType;

public class Second1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second1);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImgType(Data.PNG);
            }
        });
    }

   //基本注解使用
    public void setImgType(@ImgType int type) {
        Toast.makeText(this, String.valueOf(type), Toast.LENGTH_SHORT).show();
    }

}
