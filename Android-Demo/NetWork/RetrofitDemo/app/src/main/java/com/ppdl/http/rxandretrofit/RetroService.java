package com.ppdl.http.rxandretrofit;

import com.ppdl.bean.WeChatBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RetroService {

    @FormUrlEncoded
    @POST("/weixin/query")
    public Observable<WeChatBean> rxpost(@Field("pno") int pno,
                                         @Field("ps") int ps,
                                         @Field("dtype") String dtype,
                                         @Field("key") String key);

}
