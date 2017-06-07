package com.wx.proxy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wx.proxy.R;
import com.wx.proxy.test2.BindView;
import com.wx.proxy.test2.Butterknife;
import com.wx.proxy.test2.LayoutId;

@LayoutId(R.layout.activity_second)
public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button mBtn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Butterknife.inject(this);
        mBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this,"Id注解",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
