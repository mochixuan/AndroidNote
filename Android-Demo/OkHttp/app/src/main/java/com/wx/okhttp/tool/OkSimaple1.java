package com.wx.okhttp.tool;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wangxuan on 2018/1/17.
 */

public class OkSimaple1 {

    private static OkSimaple1 INSTANCE;

    private final String TAG = this.getClass().getSimpleName();

    public static OkSimaple1 getInstance() {
        if (INSTANCE == null) {
            synchronized (OkSimaple1.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OkSimaple1();
                }
            }
        }
        return INSTANCE;
    }

    public void get(String url, final int key) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,key+" onFailure: ");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,key+" onResponse: "+response.body().string());
            }
        });
    }

    //传入参数为json
    public void postJson(String url, final int key, String json) {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,key+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,key+" onResponse: "+response.body().string());
            }
        });
    }

    //表单形式
    public void postForm(String url, final int key, RequestBody body) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(body).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,key+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,key+" onResponse: "+response.body().string());
            }
        });
    }

    //上传文件
    public void postFile() {
        OkHttpClient httpClient = new OkHttpClient();
    }


    public OkHttpClient.Builder getBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder;
    }

}
