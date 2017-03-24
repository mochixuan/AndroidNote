package com.ppdl.rxjava;

import android.os.Bundle;
import android.view.View;

import com.ppdl.rxjava.base.BaseActivty;
import com.ppdl.rxjava.rx.FiveActivity;
import com.ppdl.rxjava.rx.FourActivity;
import com.ppdl.rxjava.rx.RxBusActivity;
import com.ppdl.rxjava.rx.SecondActivity;
import com.ppdl.rxjava.rx.ThreeActivity;

public class MainActivity extends BaseActivty implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void InitView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                openActivity(SecondActivity.class);
                break;
            case R.id.btn2:
                openActivity(FourActivity.class);
                break;
            case R.id.btn3:
                openActivity(ThreeActivity.class);
                break;
            case R.id.btn4:
                openActivity(FiveActivity.class);
                break;
            case R.id.btn5:
                openActivity(RxBusActivity.class);
                break;
            case R.id.btn6:
                break;
        }
    }

}
