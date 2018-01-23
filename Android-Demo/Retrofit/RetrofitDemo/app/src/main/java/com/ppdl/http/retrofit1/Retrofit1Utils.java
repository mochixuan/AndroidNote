package com.ppdl.http.retrofit1;

import android.util.Log;

import com.ppdl.bean.GitModel;
import com.ppdl.bean.JhPostPara;
import com.ppdl.bean.WeChatBean;
import com.ppdl.constant.Constant;
import com.ppdl.retrofitdemo.MainActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit1Utils {

    public static void baseRequest(final MainActivity activity){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.GIT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseInf.GitApi gitApi=retrofit.create(BaseInf.GitApi.class);
        Call<GitModel> modelCall=gitApi.getFeed(Constant.GIT_MY_NAME);
        modelCall.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                activity.setTextThread(response.body().getName());
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.e("baseRequest","=========onFailure>> "+ t.toString());
            }
        });
    }

    public static void basePostRequest(final MainActivity activity){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.JH_WECHAT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseInf.GitApi gitApi=retrofit.create(BaseInf.GitApi.class);
        Call<WeChatBean> call=gitApi.post(new JhPostPara(1,11,"json","97ccfd2896c2fe7d5662510254995d63"));
        call.enqueue(new Callback<WeChatBean>() {
            @Override
            public void onResponse(Call<WeChatBean> call, Response<WeChatBean> response) {
                activity.setTextThread(response.body().getReason());
            }

            @Override
            public void onFailure(Call<WeChatBean> call, Throwable t) {
                Log.e("baseRequest","=========onFailure>> "+ t.toString());
            }
        });
    }

    /*public static void baseFieldRequest(final MainActivity activity){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.JH_WECHAT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseInf.GitApi gitApi=retrofit.create(BaseInf.GitApi.class);
        Call<WeChatBean> call=gitApi.form(1,11,"json","97ccfd2896c2fe7d5662510254995d63");
        call.enqueue(new Callback<WeChatBean>() {
            @Override
            public void onResponse(Call<WeChatBean> call, Response<WeChatBean> response) {
                activity.setTextThread(response.body().getReason());
            }

            @Override
            public void onFailure(Call<WeChatBean> call, Throwable t) {
                Log.e("baseRequest","=========onFailure>> "+ t.toString());
            }
        });

    }*/

    public static void baseFieldRequest(final MainActivity activity){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.QT_BASE_START)
                .build();
        BaseInf.GitApi gitApi=retrofit.create(BaseInf.GitApi.class);
        Call<ResponseBody> call=gitApi.formqt(Constant.QT_CLIENT_ID,Constant.QT_CLIENT_SECRET);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println("=========OnSuccess0>> "+response.body().toString());
                }else{
                    try {
                        System.out.println("=========OnSuccess1>> "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("baseRequest","=========onFailure>> "+ t.toString());
            }
        });

    }

}
