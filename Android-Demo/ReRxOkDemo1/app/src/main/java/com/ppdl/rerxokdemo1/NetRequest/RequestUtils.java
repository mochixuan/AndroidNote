package com.ppdl.rerxokdemo1.NetRequest;

import com.ppdl.rerxokdemo1.Bean.SecretBean;
import com.ppdl.rerxokdemo1.Bean.VerifyBean;
import com.ppdl.rerxokdemo1.Constant.Constant;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestUtils {

    public static void getToken(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.QT_BASE_START)
                .build();
        HttpService service=retrofit.create(HttpService.class);
        Call<ResponseBody> call=service.getToken(Constant.QT_CLIENT_ID, Constant.QT_CLIENT_SECRET);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println("========OnSuccess>> "+response.body().toString());
                }else{
                    try {
                        System.out.println("========OnSuccess>> "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("========onFailure>> "+t.toString());
            }
        });
    }

}
