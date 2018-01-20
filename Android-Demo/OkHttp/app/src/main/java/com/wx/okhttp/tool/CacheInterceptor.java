package com.wx.okhttp.tool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 如果服务器不支持缓存就可能没有指定这个头部，或者指定的值是如no-store等，
 * 但是我们还想在本地使用缓存的话要怎么办呢？这种情况下我们就需要使用Interceptor来重写Respose的头部信息，
 * 从而让okhttp支持缓存。
 * Created by wangxuan on 2018/1/19.
 */

public class CacheInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request())
                .newBuilder()
                .removeHeader("pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control","max-age="+3600*24)
                .build();
        return response;
    }
}
