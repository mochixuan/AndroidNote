package com.ppdl.rxjava.rx.RxBus2;

import com.ppdl.rxjava.rx.RxBus1.RxBus1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

//写的不怎么好
public class RxBus2 {

    private static volatile RxBus2 mInstance;
    private final Subject<Object,Object> mSubject;
    private Map<Class<?>, Object> mSubscriptionMap;        /*compositeSubscription:可以存很多*/

    private RxBus2() {
        mSubject = new SerializedSubject<>(PublishSubject.create());
        mSubscriptionMap = new ConcurrentHashMap<>();
    }

    public static RxBus2 getInstance(){
        if(mInstance==null){
            synchronized (RxBus1.class){
                if(mInstance==null){
                    mInstance=new RxBus2();
                }
            }
        }
        return mInstance;
    }

    /*发送事件*/
    public void post(Object object){
        mSubject.onNext(object);
    }

    /*发送新sticky事件*/
    public void postSticky(Object object) {
        synchronized (mInstance) {
            mSubscriptionMap.put(object.getClass(), object);
        }
        post(object);
    }

    /*返回指定类型的Observable实例 获取这个类型的实例*/
    public <T> rx.Observable<T> toObservable(final Class<T> type){
        return mSubject.ofType(type);
    }

    /*返回指定类型的粘性Observable实例 获取这个类型的实例*/
    public <T> Observable<T> toObservableSticky(final Class<T> type) {
        synchronized (mSubscriptionMap) {
            Observable<T> observable = toObservable(type);
            final Object event = mSubscriptionMap.get(type);
            if (event != null) {
                return observable.mergeWith(Observable.create(new Observable.OnSubscribe<T>() {         //自发
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        subscriber.onNext(type.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    /*是否已有观察者订阅*/
    public boolean hasObservers(){
        return mSubject.hasObservers();
    }

    /*根据ype获取粘性事件*/
    public <T> T getStickyEvent(Class<T> type) {
        synchronized (mSubscriptionMap) {
            return type.cast(mSubscriptionMap.get(type));
        }
    }

    /*移除指定type的粘性事件*/
    public <T> T removeStickyEvent(Class<T> type) {
        synchronized (mSubscriptionMap) {
            return type.cast(mSubscriptionMap.remove(type));
        }
    }

    /*移除所以粘性事件*/
    public void removeAllStickyEvents() {
        synchronized (mSubscriptionMap) {
            mSubscriptionMap.clear();
        }
    }

}
