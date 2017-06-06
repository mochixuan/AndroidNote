package com.ppdl.rxjava.rx2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rxjava.R;
import com.ppdl.rxjava.base.BaseActivty;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RxJava2Activity extends BaseActivty implements View.OnClickListener {

    private static final String TAG = "RXJava2";
    private TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);
    }

    @Override
    public void InitView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                base();
                break;
            case R.id.btn2:
                initbackPressure();
                break;
            case R.id.btn3:
                if (subscription != null) subscription.request(127);
                break;
            case R.id.btn4:
                testDispose();
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
        }
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

    private void base() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                setTvReceivers("开始"+d);
            }

            @Override
            public void onNext(Integer integer) {
                setTvReceivers(integer+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                setTvReceivers("完成");
            }
        });
    }

    Subscription subscription;
    private void initbackPressure() {
        if (subscription!=null) return;
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                System.out.println("==================Start=======================");
                for (int i=0;i<10000;i++) {
                    e.onNext(i);
                    System.out.println("==============================send>>"+i);
                }
            }
        }, BackpressureStrategy.LATEST);

        org.reactivestreams.Subscriber subscriber = new org.reactivestreams.Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("==================onSubscribe=======================");
                subscription = s;
            }

            @Override
            public void onNext(Object o) {
                System.out.println("==============================recevicer>>"+o);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("==================onComplete=======================");
            }
        };
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void testDispose() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i= 0;i<20;i++) {
                    Log.d(TAG,"===============================onNext0>>"+i);
                    if (e.isDisposed()) {
                        e.onComplete();
                        break;
                    }
                    e.onNext(i);
                }
            }
        })
        .doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.w(TAG,"================================================doOnDispose");
            }
        })
        .subscribe(new Observer<Integer>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                if (integer==10) {
                    mDisposable.dispose();
                }
                Log.d(TAG,"======================================onNext1>>"+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG,"================================================onError");
            }

            @Override
            public void onComplete() {
                Log.w(TAG,"================================================onComplete");
            }
        });
    }

}
