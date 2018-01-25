package com.ppdl.http.loadretrofit;

import com.ppdl.constant.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface LoadService {

    @Streaming
    @GET(Constant.BAIDU_IMG_END)
    Call<ResponseBody> downFile();

}
