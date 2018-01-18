package com.ppdl.rxjava;

import android.os.Bundle;
import android.view.View;

import com.ppdl.rxjava.base.Base2Activty;
import com.ppdl.rxjava.rx.FiveActivity;
import com.ppdl.rxjava.rx.FourActivity;
import com.ppdl.rxjava.rx.RxBusActivity;
import com.ppdl.rxjava.rx.SecondActivity;
import com.ppdl.rxjava.rx.ThreeActivity;
import com.ppdl.rxjava.rx2.RxJava2Activity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Base2Activty implements View.OnClickListener{

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
                //test1();
                //test(this.bindUntilEvent(ActivityEvent.STOP));
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
                openActivity(RxJava2Activity.class);
                break;
        }
    }

    private void test1() {
        Observable.just(true)
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Boolean>() {
                    @Override
                    public boolean test(Boolean aBoolean) {
                        System.out.println("=============================================1");
                        return aBoolean;
                    }
                }).map(new Function<Boolean, Observable<String>>() {
                    @Override
                    public Observable<String> apply(@NonNull Boolean aBoolean) throws Exception {
                        System.out.println("=============================================2");
                        return Observable.just("as");
                    }
                })
                .subscribe(new Observer<Observable<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Observable<String> stringObservable) {
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void test(com.trello.rxlifecycle2.LifecycleTransformer transformer) {
        Observable.intervalRange(2,10,0,1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .compose(transformer)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Long>() {
                    @Override
                    public boolean test(Long integer) {
                        System.out.println("====================================================11>>>> "+integer);
                        if (integer>=3) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Observer<Long>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("====================================================00");
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("====================================================22>>> "+aLong);
                        //disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("====================================================33");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("====================================================44");
                    }
                });
    }

    private Observable<Integer> mObservable;
    private Observable<Integer> getObserval() {
        if (mObservable==null) {
            mObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    System.out.println("==============================================================11");
                }
            });
        }
        return mObservable;
    }

    private void subscriber() {
        getObserval()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println("=============================================2222");

            }
        });
    }

}
