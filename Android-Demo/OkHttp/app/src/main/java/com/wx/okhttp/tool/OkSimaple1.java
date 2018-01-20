package com.wx.okhttp.tool;

import android.content.Context;
import android.util.Log;

import com.wx.okhttp.tool.cookies.CookiesManager;
import com.wx.okhttp.tool.interceptor.CacheInterceptor;
import com.wx.okhttp.tool.interceptor.LoggingInterceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

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

    //上传文件 //测试https://www.pgyer.com/doc/api#uploadApp
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
                            MediaType.parse("multipart/form-data"), //蒲公英规定的
                            //MediaType.parse("text/x-markdown; charset=utf-8"),//和服务器要相同
                            //MediaType.parse("application/octet-stream")
                            file
                    );
                    builder.addFormDataPart(key,file.getName(),requestBody);
                }
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

    //文件上传带进度的
    public void uploadProgressFile(String url, HashMap<String,Object> params, final int index) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            try {
                Object object = params.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, (String) params.get(key));
                } else {
                    File file = (File) object;
                    RequestBody requestBody = createProgressRequestBody(MediaType.parse("multipart/form-data"),file);
                    builder.addFormDataPart(key,file.getName(),requestBody);
                }
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

    private RequestBody createProgressRequestBody(final MediaType mediaType, final File file) {
        RequestBody requestBody = new RequestBody() {

            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() throws IOException {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    long remaining = contentLength();
                    long current = 0;
                    for (long readCount;(readCount = source.read(buf,2048)) != -1;) {
                        sink.write(buf,readCount);
                        current += readCount;
                        Log.d(TAG,"===========upload progress>> "+(current*100f/remaining));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return requestBody;
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

    //http://blog.csdn.net/briblue/article/details/52920531
    //以上如果cache没有过去会直接返回cache而不会发起网络请求，若过期会自动发起网络请求。
    public void cacheRequest(String url, Context context,final int index) {
        CacheControl.Builder builder = new CacheControl.Builder();
        //builder.noCache();      //不使用缓存，全部走网络
        //builder.noStore();      //不使用缓存，也不存储缓存
        //builder.onlyIfCached(); //只使用缓存
        //builder.noTransform();  //禁止转码
        builder.maxAge(10, TimeUnit.SECONDS); //指示客户机可以接收生存期不大于指定时间的响应。已这个时间为准，当缓存时间大于这个时间则重新请求数据
        //builder.maxStale(20, TimeUnit.SECONDS);   //指示客户机可以接收超出超时期间的响应消息
        //builder.minFresh(20, TimeUnit.SECONDS);   //指示客户机可以接收响应时间小于当前时间加上指定时间的响应。
        //CacheControl.FORCE_CACHE;     //仅仅使用缓存
        //CacheControl.FORCE_NETWORK;   // 仅仅使用网络

        final Request request;
        /*if (NetworkUtil.isConnect(context.getApplicationContext())==NetworkUtil.NOCONNECT) {
            request = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE).url(url).build();
        } else {
            request = new Request.Builder().url(url).build();
        }*/
        request = new Request.Builder().cacheControl(builder.build()).url(url).build();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                //OkHttp 提供了对用户认证的支持。当 HTTP 响应的状态代码是 401 时，OkHttp 会从设置的 Authenticator 对象中获取到新的 Request 对象并再次尝试发出请求。
                // Authenticator 接口中的 authenticate 方法用来提供进行认证的 Request 对象.
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic("user","password");
                        return response.request()
                                .newBuilder()
                                .header("Authorization",credential)
                                .build();
                    }
                })
                //设置缓存大小和位置
                .cache(new Cache(context.getCacheDir(),10*1024*1024))
                //设置网络过滤器
                .addNetworkInterceptor(new CacheInterceptor())
                .build()
        ;

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.w(TAG,index+" onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,index+" onResponse: "+response.code()+" cache:"+response.cacheControl()+"  network:"+response.networkResponse()+"  "+response.body().string());
            }
        });
        //call.cancel();

    }


    /*private CookieJar mCookieJar = new CookieJar() {
        private Map<String,List<Cookie>> cookieMap = new HashMap<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieMap.put(url.host(),cookies);
            for (int i = 0;i<cookies.size();i++) {
                Cookie cookie = cookies.get(i);
                Log.d(TAG,"======saveCookie>> path:"+cookie.path()+"  domain:"+cookie.domain()+"  name:"+cookie.name()+"  value:"+cookie.value());
            }
            Log.d(TAG,"==========save>>"+url.host()+"  "+cookies.size());
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieMap.get(url.host());
            if (cookies == null) {
                cookies = new ArrayList<>();
                Log.d(TAG,"==========load>>"+url.host()+"  null");
            } else {
                Log.d(TAG,"==========load>>"+url.host()+"  "+cookies.size());
            }
            for (int i = 0;i<cookies.size();i++) {
                Cookie cookie = cookies.get(i);
                Log.d(TAG,"======loadCookie>> path:"+cookie.path()+"  domain:"+cookie.domain()+"  name:"+cookie.name()+"  value:"+cookie.value());
            }
            return cookies;
        }
    };*/

    public void cookie(String url ,Context context,HashMap<String,String> params,final int index) {

        FormBody.Builder formBuidler = new FormBody.Builder();
        for (String key: params.keySet()) {
            formBuidler.add(key,params.get(key));
        }
        final Request request = new Request.Builder()
                .url(url)
                .post(formBuidler.build())
                .build();
        mOkHttpClient.newBuilder()
                .cookieJar(new CookiesManager(context))
                .build()
                .newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.w(TAG,index+" onFailure: ");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG,"=======>>"+request.headers().get("Cookie")+" :  "+response.header("Cookie")+"  :  "+response.body().string());
                    }
                });

    }

    public void interceptor(String url, Context context,final int index) {
        CacheControl.Builder builder = new CacheControl.Builder();
        builder.maxAge(10, TimeUnit.SECONDS);
        final Request request = new Request.Builder().cacheControl(builder.build()).url(url).build();

        mOkHttpClient.newBuilder()
                //设置缓存大小和位置
                .cache(new Cache(context.getCacheDir(),10*1024*1024))
                .addInterceptor(new LoggingInterceptor()) //请求时先进过
                //设置网络过滤器
                .addNetworkInterceptor(new CacheInterceptor()) //相应时先进过
                .retryOnConnectionFailure(true) //连接失败后重试
                .build()
                .newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.w(TAG,index+" onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG,"=========>>"+response.body().string());
                    }
                });
    }

}
