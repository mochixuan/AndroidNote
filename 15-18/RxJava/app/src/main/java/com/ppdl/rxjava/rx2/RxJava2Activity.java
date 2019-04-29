package com.ppdl.rxjava.rx2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.ppdl.rxjava.FlatMap.Course;
import com.ppdl.rxjava.R;
import com.ppdl.rxjava.base.BaseActivty;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func1;

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
        findViewById(R.id.btn9).setOnClickListener(this);
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
                scan();
                break;
            case R.id.btn6:
                empty();
                break;
            case R.id.btn9:
                tryWhen();
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

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

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

    private void scan() {
        Observable.just("1","2","3","4","5")
                .scan("wang",new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        Log.d(TAG,"====scan apply>>"+s+"  "+s2);
                        return s+s2;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG,"====scan accept>>"+s);
            }
        });
    }

    private void debounce() {
        RxTextView.textChanges((TextView) findViewById(R.id.tv_receiver))
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return null;
                    }
                });
    }

    private void empty() {
        Observable.empty()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG,"=======================>>"+o);
                    }
                });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        org.reactivestreams.Subscriber<Integer> subscriber = new org.reactivestreams.Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void combineLatest() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
    }

    int temp = 0;
    private void tryWhen() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                temp++;
                Log.d(TAG,"===========subscribe>>"+temp);
                e.onError(new IOException("IO错误"+temp));
                //e.onNext("0");
            }
        })
        /*.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Throwable throwable) throws Exception {
                Log.d(TAG,"===========onErrorResumeNext>>"+throwable.getMessage());
                return Observable.just("我把错误改了，上个错误是："+throwable.getMessage());
            }
        })*/
        .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                //Log.d(TAG,"===========retryWhen>>");
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof IOException) {
                            if (temp < 3) {
                                return Observable.timer(1,TimeUnit.SECONDS);
                            }
                            Log.d(TAG,"===========retryWhen1>>"+temp);
                            return Observable.error(new IOException("我把你改了,你没救了。"));
                        }
                        Log.d(TAG,"===========retryWhen2>>"+temp);
                        return Observable.error(throwable);
                    }
                });
            }
        })
        /*.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                Log.d(TAG,"===========repeatWhen>>");
                return Observable.just("repeatWhen");
            }
        })*/
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"===========onNext>>"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"===========onError>>"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"===========onComplete>>");
            }
        });
    }

    public void merge() {
        List<Course> courses1 = new ArrayList<>();
        courses1.add(new Course("语文1 - 1"));
        courses1.add(new Course("语文1 - 2"));
        courses1.add(new Course("语文1 - 3"));
        List<Course> courses2 = new ArrayList<>();
        courses2.add(new Course("数学2 - 1"));
        courses2.add(new Course("数学2 - 2"));
        courses2.add(new Course("数学2 - 3"));


    }

}