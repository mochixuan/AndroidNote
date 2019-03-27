package com.ppdl.rxjava.rx;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rxjava.R;
import com.ppdl.rxjava.rxbus.RxBus;
import com.ppdl.rxjava.rxbus.RxLifeBus;
import com.ppdl.rxjava.rxbus.RxStickyBus;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxBusActivity extends RxAppCompatActivity implements View.OnClickListener {

    private TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                subcriberString();
                break;
            case R.id.btn2:
                subcriberRxlife();
                break;
            case R.id.btn3:
                subcriberSticky();
                break;
            case R.id.btn4:
                RxBus.getInstance().post("来自按钮三");
                break;
            case R.id.btn5:
                RxLifeBus.getInstance().post("大家好");
                break;
            case R.id.btn6:
                RxStickyBus.getInstance().postSticky("粘性事件");
                break;
        }
    }

    public void setTvReceivers(String data){
        tvReceiver.setText(data);
    }

    public void subcriberString() {
        RxBus.getInstance()
                .addSubscription(this, RxBus.getInstance().toObservable(String.class)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                setTvReceivers(e.getMessage());
                            }

                            @Override
                            public void onNext(String s) {
                                setTvReceivers(s);
                            }
                        }));
    }

    public void subcriberRxlife() {
        RxLifeBus.getInstance()
                .toObservable(String.class,this.bindToLifecycle())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setTvReceivers(s);
                    }
                });

    }

    public void subcriberSticky() {
        RxStickyBus.getInstance()
                .toObservableSticky(String.class)
                .compose(this.<String>bindToLifecycle())        //解决内存溢出
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setTvReceivers(s);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
    }

}
