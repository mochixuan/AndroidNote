package com.wx.okhttp.tool;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wangxuan on 2018/1/17.
 */

public class OkSimaple1 {

    private static OkSimaple1 INSTANCE;
    private OkHttpClient mOkHttpClient;

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

    private OkSimaple1() {
        //mOkHttpClient = new OkHttpClient();
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)  //设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)     //设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)    //设置写入超时时间
                .build();
    }

    private Request.Builder mRequestBuilder;
    private Request.Builder getRequestBuilder() {
        if (mRequestBuilder == null) {
            mRequestBuilder = new Request.Builder()
                    .addHeader("Connection", "keep-alive");
                    //.addHeader("platform", "2")
                    //.addHeader("phoneModel", Build.MODEL)
                    //.addHeader("systemVersion", Build.VERSION.RELEASE)
                    //.addHeader("appVersion", "3.2.0");
        }
        return mRequestBuilder;
    }

    public void get(String baseUrl, HashMap<String,String> params,final int index) {
        StringBuffer tempParams = new StringBuffer();
        tempParams.append(baseUrl);
        if (params != null) {
            tempParams.append("?");
            int pos = 0;
            for (String key : params.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                pos = 1;
                try {
                    tempParams.append(String.format("%s=%s",key, URLEncoder.encode(params.get(key),"UTF-8")));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        Request request = getRequestBuilder().url(tempParams.toString()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,index+" onResponse: "+response.body().string());
            }
        });
    }

    //传入参数为json
    public void postJson(String baseUrl, HashMap<String,String> params , final int index) {
        StringBuffer tempParams = new StringBuffer();
        tempParams.append(baseUrl);
        tempParams.append("{");
        for (String key : params.keySet()) {
            try {
                tempParams.append(String.format("%s:%s",key, URLEncoder.encode(params.get(key),"UTF-8")));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        tempParams.append("}");
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                tempParams.toString());
        Request request = getRequestBuilder().url(baseUrl).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,index+" onResponse: "+response.body().string());
            }
        });
    }

    //表单形式
    public void postForm(String url, HashMap<String,String> params, final int index) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            try {
                builder.add(key,URLEncoder.encode(params.get(key),"UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Request request = getRequestBuilder().url(url).post(builder.build()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,index+" onResponse: "+response.body().string());
            }
        });
    }

    //上传文件
    public void uploadFile(String url, HashMap<String,Object> params, final int index) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            try {
                Object object = params.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, (String) params.get(key));
                } else {
                    File file = (File) object;
                    RequestBody requestBody = RequestBody.create(
                            MediaType.parse("text/x-markdown; charset=utf-8"),//和服务器要相同
                            //MediaType.parse("application/octet-stream")
                            file
                    );
                    builder.addFormDataPart(key,file.getName(),requestBody);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Request request = getRequestBuilder().post(builder.build()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,index+" onResponse: "+response.body().string());
            }
        });
    }

    //文件下载
    public void downFile(String fileUrl,String filePath,final int index) {
        final File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        Request request = getRequestBuilder().url(fileUrl).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                saveFile(response,file);
            }
        });
    }

    private void saveFile(Response response,File file) {
        if (response.isSuccessful()) {
            FileOutputStream fos = null;
            InputStream is = null;
            try {
                fos = new FileOutputStream(file);
                long total = response.body().contentLength();
                long current = 0;
                is = response.body().byteStream();
                int len = 0;
                byte[] buf = new byte[2048];
                while ((len = is.read(buf))!= -1) {
                    current += len;
                    fos.write(buf,0,len);
                    Log.d(TAG,"=======file progress: "+(current*100f/total));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public OkHttpClient.Builder getBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder;
    }

}
