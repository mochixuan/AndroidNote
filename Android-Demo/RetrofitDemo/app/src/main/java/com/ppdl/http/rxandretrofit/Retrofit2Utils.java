package com.ppdl.http.rxandretrofit;

import android.util.Log;

import com.ppdl.bean.WeChatBean;
import com.ppdl.constant.Constant;
import com.ppdl.retrofitdemo.MainActivity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Retrofit2Utils {

    public static void baseRxFieldRequest(final MainActivity activity){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.JH_WECHAT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RetroService retroService=retrofit.create(RetroService.class);

        Observable<WeChatBean> observable=retroService.rxpost(1,11,"json","97ccfd2896c2fe7d5662510254995d63");
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<WeChatBean>() {
                    @Override
                    public void call(WeChatBean weChatBean) {
                        activity.setTextUI(weChatBean.getReason());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("baseRequest","=========onFailure>> "+ throwable.toString());
                    }
                });
    }

}
