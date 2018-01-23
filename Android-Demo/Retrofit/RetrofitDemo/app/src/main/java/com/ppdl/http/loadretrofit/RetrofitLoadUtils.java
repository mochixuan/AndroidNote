package com.ppdl.http.loadretrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ppdl.constant.Constant;
import com.ppdl.retrofitdemo.LoadActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitLoadUtils {

    public static void loadRequest(final LoadActivity activity){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BAIDU_IMG_BASE)
                .build();
        LoadService loadService=retrofit.create(LoadService.class);
        Call<ResponseBody> call=loadService.downFile();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Bitmap bitmap=BitmapFactory.decodeStream(response.body().byteStream());
                activity.setImgThread(bitmap);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
