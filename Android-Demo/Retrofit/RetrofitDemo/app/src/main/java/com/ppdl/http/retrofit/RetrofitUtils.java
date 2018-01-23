package com.ppdl.http.retrofit;

import com.ppdl.constant.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {

    public static Call<ResponseBody>  baseRetRequest(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.DATA_JH_BASE)
                .build();
        ServiceInterface requestService=retrofit.create(ServiceInterface.class);
        Call<ResponseBody> call=requestService.getString();
        return call;
    }

    public static void baseRequest1(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.DATA_JH_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ServiceInterface serviceInterface=retrofit.create(ServiceInterface.class);

        Call<String> call=serviceInterface.getOriString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.print("======OnSuccess>>"+response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.print("======onFailure>>"+t.toString());
            }
        });
    }

}
