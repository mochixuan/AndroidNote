package com.ppdl.myapplication.Http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseInf{

    public interface GitApi{

        /*表单模式*/
        @FormUrlEncoded
        @POST("/weixin/query")
        Call<ResponseBody> form(@Field("pno") int pno,
                                @Field("ps") int ps,
                                @Field("dtype") String dtype,
                                @Field("key") String key);

    }



}
