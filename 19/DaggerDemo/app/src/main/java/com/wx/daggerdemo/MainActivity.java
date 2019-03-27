package com.wx.daggerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind( this ) ;
    }

    @OnClick(R.id.btn_butter_knife)
    protected void enterButterKnife() {
        startActivity(new Intent(this,ButterKnifeActivity.class));
    }

    @OnClick(R.id.btn_recycler)
    protected void enterRecyclerView() {
        startActivity(new Intent(this,RecyclerViewActivity.class));
    }

}
