package com.wx.daggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wx.daggerdemo.bean.PersonBean;
import com.wx.daggerdemo.bean.StudentBean;
import com.wx.daggerdemo.dagger.component.DaggerDagger2ActivityComponent;
import com.wx.daggerdemo.dagger.module.StudentModule;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Dagger2Activity extends AppCompatActivity {

    @Inject @Named("params") StudentBean studentBean1;
    @Inject @Named("noParams") StudentBean studentBean2;
    @Inject OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        ButterKnife.bind(this);
        // DaggerDagger2ActivityComponent.create().inject(this);
        DaggerDagger2ActivityComponent
                .builder()
                .studentModule(new StudentModule(new PersonBean("","莫辞旋","")))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btn_test1)
    protected void OnClick1() {
        Toast.makeText(this, studentBean1.getPersonBean().getName(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, studentBean2.getPersonBean().getName(),Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_test2)
    protected void OnClick2() {
        Request request = new Request
                .Builder()
                .url("https://www.baidu.com/")
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(Dagger2Activity.this, "失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    Toast.makeText(Dagger2Activity.this, "成功",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }

}
