package com.wx.okhttp.tool.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 如果服务器不支持缓存就可能没有指定这个头部，或者指定的值是如no-store等，
 * 但是我们还想在本地使用缓存的话要怎么办呢？这种情况下我们就需要使用Interceptor来重写Respose的头部信息，
 * 从而让okhttp支持缓存。
 * 分为两步，一个是客户端的请求设置缓存，但必须要在response设置为带缓存这样okhttp才会将数据缓存起来,
 * Created by wangxuan on 2018/1/19.
 */

public class CacheInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request())
                .newBuilder()
                .removeHeader("pragma") //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .removeHeader("Cache-Control")
                .header("Cache-Control","max-age="+3600*24)
                .build();
        return response;
    }
}
