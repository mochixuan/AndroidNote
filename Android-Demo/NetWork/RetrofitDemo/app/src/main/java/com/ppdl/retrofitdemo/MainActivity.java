package com.ppdl.retrofitdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ppdl.http.retrofit.RetrofitUtils;
import com.ppdl.http.retrofit1.Retrofit1Utils;
import com.ppdl.http.rxandretrofit.Retrofit2Utils;

import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ppdl.http.retrofit.RetrofitUtils.baseRetRequest;

public class MainActivity extends BaseActivity{

    private TextView tvReceiver;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void InitView() {
        tvReceiver = setTextView(R.id.tv_receiver);
        setButton(R.id.btn_1);
        setButton(R.id.btn_2);
        setButton(R.id.btn_3);
        setButton(R.id.btn_4);
        setButton(R.id.btn_5);
        setButton(R.id.btn_6);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                baseRequest();
                RetrofitUtils.baseRequest1();
                break;
            case R.id.btn_2:
                Retrofit1Utils.baseRequest(this);
                break;
            case R.id.btn_3:
                Retrofit1Utils.basePostRequest(this);
                break;
            case R.id.btn_4:
                Retrofit1Utils.baseFieldRequest(this);
                break;
            case R.id.btn_5:
                Retrofit2Utils.baseRxFieldRequest(this);
                break;
            case R.id.btn_6:
                Intent intent=new Intent(MainActivity.this,LoadActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void baseRequest() {
        Call<ResponseBody> call=RetrofitUtils.baseRetRequest();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                setTextThread(tvReceiver,anayleJson(response.body().charStream()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
