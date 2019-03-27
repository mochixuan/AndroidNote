package com.ppdl.http.retrofit;

import com.ppdl.constant.Constant;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceInterface {

    /*Call<ResponseBody> ResponseBody:返回的数据属性*/
    @GET("calendar/day?date=2016-11-1&key=c2f556773f6f935218a1fdaea10b4587")
    public Call<ResponseBody> getString();

    @GET("calendar/day?date=2016-11-1&key=c2f556773f6f935218a1fdaea10b4587")
    public Call<String> getOriString();

}
