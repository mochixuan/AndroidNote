package com.ppdl.retrofitdemo;

import android.view.View;

import com.ppdl.http.loadretrofit.RetrofitLoadUtils;
import com.ppdl.http.retrofit.RetrofitUtils;
import com.ppdl.http.retrofit1.Retrofit1Utils;
import com.ppdl.http.rxandretrofit.Retrofit2Utils;

public class LoadActivity extends BaseActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_load;
    }

    @Override
    public void InitView() {
        setTextView(R.id.tv_receiver);
        setImageView(R.id.iv_img);

        setButton(R.id.btn_1);
        setButton(R.id.btn_2);
        setButton(R.id.btn_3);
        setButton(R.id.btn_4);
        setButton(R.id.btn_5);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_1:
                RetrofitLoadUtils.loadRequest(this);
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
        }

    }

}
