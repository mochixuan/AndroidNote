package com.wx.okhttp;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.okhttp.base.BaseActivity;
import com.wx.okhttp.databinding.ActivityMainBinding;
import com.wx.okhttp.tool.OkSimaple1;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private static final String WEIXIN_GET_URL = "http://v.juhe.cn/weixin/query?pno=&ps=&dtype=&key=97ccfd2896c2fe7d5662510254995d63";
    private static final String WEIXIN_POST_URL = "http://v.juhe.cn/weixin/query";
    private static final String BAIDU_URL = "https://www.baidu.com/";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnGet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkSimaple1.getInstance().get(WEIXIN_GET_URL,0);
                OkSimaple1.getInstance().get(BAIDU_URL,1);
            }
        });
        binding.btnPost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("pno",1);
                    jsonObject.put("ps",10);
                    jsonObject.put("dtype","json");
                    jsonObject.put("key","97ccfd2896c2fe7d5662510254995d63");
                    OkSimaple1.getInstance().postJson(WEIXIN_POST_URL,0,jsonObject.toString());


                    RequestBody requestBody = new FormBody.Builder()
                            .add("pno", String.valueOf(1))
                            .add("ps", String.valueOf(10))
                            .add("dtype","json")
                            .add("key","97ccfd2896c2fe7d5662510254995d63")
                            .build();
                    OkSimaple1.getInstance().postForm(WEIXIN_POST_URL,1,requestBody);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
