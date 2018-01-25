package com.wx.okhttp.tool.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangxuan on 2018/1/20.
 */

public class LoggingInterceptor implements Interceptor{

    private final String TAG = this.getClass().getSimpleName();
    private final String LOG_HEADER = "========>>";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.nanoTime();

        Log.d(TAG,LOG_HEADER+String.format("Request %s on %s%n%s",
                request.url(),chain.connection(),request.headers()));

        //可能类似于redux的执行，这里已经开始执行了
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();

        Log.d(TAG,LOG_HEADER+String.format("Response for %s in %.1fms%n%s",
                response.request().url(),(t2 - t1)/1e6d,response.headers()));

        return response;
    }

}
