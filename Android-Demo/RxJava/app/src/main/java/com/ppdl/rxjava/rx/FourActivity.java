package com.ppdl.rxjava.rx;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.ppdl.rxjava.R;
import com.ppdl.rxjava.base.BaseActivty;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FourActivity extends BaseActivty {

    private TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
    }

    @Override
    public void InitView() {
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);

        RxView.clicks(findViewById(R.id.btn1))
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        setTvReceivers("单击了");
                    }
                });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.interval(1,TimeUnit.SECONDS)
                        .compose(FourActivity.this.<Long>bindUntilEvent(ActivityEvent.STOP))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                setTvReceiver(aLong+"",2);
                            }
                        });
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.timer(1,TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                setTvReceiver(aLong+"",3);
                            }
                        });
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void setTvReceiver(String data,int num){
        tvReceiver.setText("接收到按钮"+num+":"+data);
    }

    public void setTvReceivers(String data){
        if(tvReceiver.getText().toString().isEmpty()){
            tvReceiver.setText(data);
        }else{
            tvReceiver.setText(tvReceiver.getText().toString()+" : "+data);
        }

    }

    @Override
    public void InitData() {

    }


}
