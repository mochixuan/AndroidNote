package com.wx.daggerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wx.daggerdemo.recycler.Refresh2Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_recycler_refresh2)
    protected void enterRefresh2() {
        startActivity(new Intent(this, Refresh2Activity.class));
    }

}
