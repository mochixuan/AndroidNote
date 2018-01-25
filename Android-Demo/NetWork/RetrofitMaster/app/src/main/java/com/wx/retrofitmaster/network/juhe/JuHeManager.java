package com.wx.retrofitmaster.network.juhe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.wx.retrofitmaster.bean.JhPostParam;
import com.wx.retrofitmaster.bean.WeChatBean;
import com.wx.retrofitmaster.constant.DataConstants;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wangxuan on 2018/1/22.
 */

public class JuHeManager {

    private final String TAG = this.getClass().getSimpleName();
    private static JuHeManager INSTANCE;

    public static JuHeManager getInstance() {
        if (INSTANCE == null) {
            synchronized (JuHeManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JuHeManager();
                }
            }
        }
        return INSTANCE;
    }

    private Retrofit mRetrofit;
    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(DataConstants.JUHE_BASE_URL)
                    //.client(client)
                    .build();
        }
        return mRetrofit;
    }

    private JuHeService mJuHeService;
    private JuHeService getApiService() {
        if (mJuHeService == null) {
            mJuHeService = getRetrofit().create(JuHeService.class);
        }
        return mJuHeService;
    }

    public void getWeChatSelection1(final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection1();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+Thread.currentThread()+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection2(final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection2("query");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection3(final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection3();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+Thread.currentThread()+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection4(int pno,int ps,String dtype,String key,final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection4(pno, ps, dtype, key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+response.isSuccessful()+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection5(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JuHeService.class);
        JhPostParam param = new JhPostParam(1,11,"json","97ccfd2896c2fe7d5662510254995d63");
        Call<WeChatBean> call = juHeService.getWeChatSelection5(param);
        call.enqueue(new Callback<WeChatBean>() {
            @Override
            public void onResponse(Call<WeChatBean> call, Response<WeChatBean> response) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+response.body().getReason());
            }

            @Override
            public void onFailure(Call<WeChatBean> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection6(int pno,int ps,String dtype,String key,final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection6(pno, ps, dtype, key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    //会内存泄漏但这里我只是测试不用在原
    public void downBaiDuImg7(final int index, final ImageView imageView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataConstants.BAIDU_IMG_BASE)
                .build();
        JuHeService juHeService = retrofit.create(JuHeService.class);
        Call<ResponseBody> call = juHeService.downBaiDuImage7();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                Observable.create(new ObservableOnSubscribe<Bitmap>() {
                    @Override
                    public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                        e.onNext(BitmapFactory.decodeStream(response.body().byteStream()));
                    }
                }).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Bitmap>() {
                            @Override
                            public void accept(Bitmap bitmap) throws Exception {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                /* 这是主线程
                FileOutputStream fos = null;
                InputStream is = null;
                try {
                    File file = new File(Environment.getExternalStorageDirectory(),"test/baidu.png");
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                    }
                    byte[] bytes = new byte[1024];
                    fos = new FileOutputStream(file);
                    is = response.body().byteStream();
                    int len ;
                    while ((len = is.read(bytes)) != -1) {
                        fos.write(bytes,0,len);
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
                }*/
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void uploadFile8(String uKey,String apiKey,String installType,String password,String updateDescription,String path, final int index) {

        MediaType mediaType = MediaType.parse("multipart/form-data");
        File file = new File(path);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",file.getName(),createProgressRequestBody(mediaType,file));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataConstants.PUGONGYING_URL)
                .build();
        JuHeService juHeService = retrofit.create(JuHeService.class);
        Call<ResponseBody> call = juHeService.uploadFile8(
                DataConstants.PUGONGYING_URL,
                RequestBody.create(mediaType,uKey),
                RequestBody.create(mediaType,apiKey),
                RequestBody.create(mediaType,installType),
                RequestBody.create(mediaType,password),
                RequestBody.create(mediaType,updateDescription),
                filePart
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
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
                    long readCount ;
                    while ((readCount = source.read(buf,2048)) != -1) {
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

    public void getWeChatSelection9(final int index) {
        Call<ResponseBody> call = getApiService().getWeChatSelection9("header3");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.w(TAG,"===========onResponse>>"+index+"  "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection10(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(JuHeService.class);

        Call<WeChatBean> call = juHeService.getWeChatSelection10();
        call.enqueue(new Callback<WeChatBean>() {
            @Override
            public void onResponse(Call<WeChatBean> call, Response<WeChatBean> response) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+response.body().getReason());
            }

            @Override
            public void onFailure(Call<WeChatBean> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection11(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(JuHeService.class);

        Call<String> call = juHeService.getWeChatSelection11();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w(TAG,"===========onFailure>>"+index+"  "+t.getMessage());
            }
        });
    }

    public void getWeChatSelection12(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(JuHeService.class);

        juHeService.getWeChatSelection12()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {
                        Log.w(TAG,"===========onFailure>>"+index+"  "+weChatBean.getReason());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG,"===========onFailure>>"+index+"  "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWeChatSelection13(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(JuHeService.class);

        juHeService.getWeChatSelection13()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<WeChatBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<WeChatBean> weChatBeanResponse) {
                        Log.w(TAG,"===========Response>>"+index+"  "+weChatBeanResponse.body().getReason());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG,"===========onFailure>>"+index+"  "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWeChatSelection14(final int index) {
        JuHeService juHeService = getRetrofit().newBuilder()
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(JuHeService.class);

        juHeService.getWeChatSelection14()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> response) {
                        Log.w(TAG,"===========Response>>"+index+"  "+response.body());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG,"===========onFailure>>"+index+"  "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
