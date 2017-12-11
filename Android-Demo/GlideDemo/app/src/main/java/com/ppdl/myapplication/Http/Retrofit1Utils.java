package com.ppdl.myapplication.Http;

import com.google.gson.Gson;
import com.ppdl.myapplication.Dao.GreenDaoManager;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit1Utils {

    public static GreenDaoManager daoManager=GreenDaoManager.getInstance();

    public static void baseFieldRequest(int page){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseInf.GitApi gitApi=retrofit.create(BaseInf.GitApi.class);
        Call<ResponseBody> call=gitApi.form(page,10,"json","97ccfd2896c2fe7d5662510254995d63");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson=new Gson();
                if(response.isSuccessful()){
                    try {
                        String json=response.body().string();
                        WeChatBean weChatBean=gson.fromJson(json,WeChatBean.class);
                        EventBus.getDefault().post(weChatBean);
                        daoManager.insert(1l,json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("=======================OnFailure0");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("=======================OnFailure1");
            }
        });

    }



}
