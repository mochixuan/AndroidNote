package com.ppdl.http.retrofit1;

import com.ppdl.bean.GitModel;
import com.ppdl.bean.JhPostPara;
import com.ppdl.bean.WeChatBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseInf{

    public interface GitApi{

        @GET("/users/{user}")
        Call<GitModel> getFeed(@Path("user") String user);

        /*模拟post接口 传入的是json格式的 和下面结合*/
        @POST("/weixin/query")
        Call<WeChatBean> post(@Body JhPostPara para);

        /*表单模式*/
        @FormUrlEncoded
        @POST("/weixin/query")
        Call<WeChatBean> form(@Field("pno") int pno,
                              @Field("ps") int ps,
                              @Field("dtype") String dtype,
                              @Field("key") String key);

        /*表单模式*/
        @FormUrlEncoded
        @POST("/access?&grant_type=client_credentials")
        Call<ResponseBody> formqt(@Field("client_id") String client_id,
                                  @Field("client_secret") String client_secret);

    }



}
