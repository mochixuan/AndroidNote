package com.ppdl.rxjava.rx;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rxjava.R;
import com.ppdl.rxjava.rx.RxBus2.RxBus2;
import com.ppdl.rxjava.base.BaseActivty;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FiveActivity extends BaseActivty implements View.OnClickListener{

    private TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
    }

    @Override
    public void InitView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    @Override
    public void InitData() {



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                sendSticky();
                break;
            case R.id.btn2:
                receiverSticky();
                break;
            case R.id.btn3:
                testHandler();
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }
    }

    public void sendSticky() {
        Observable.timer(5, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .repeat(2)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        RxBus2.getInstance().postSticky("first my");
                    }
                });
    }

    public void receiverSticky() {
        RxBus2.getInstance()
                .toObservableSticky(String.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        if (tvReceiver.getText().toString().equals("")) {
                            tvReceiver.setText("收到数据:"+s);
                        } else {
                            tvReceiver.setText(tvReceiver.getText().toString()+"\n"+
                                    "收到数据:"+s);
                        }
                        RxBus2.getInstance().removeStickyEvent(String.class);
                    }
                });
    }

    public void testHandler() {
        Observable.range(1,2)
                .repeat(3)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (tvReceiver.getText().toString().equals("")) {
                            tvReceiver.setText("收到数据:"+integer);
                        } else {
                            tvReceiver.setText(tvReceiver.getText().toString()+ " 收到数据:"+integer);
                        }
                    }
                });
    }

}
