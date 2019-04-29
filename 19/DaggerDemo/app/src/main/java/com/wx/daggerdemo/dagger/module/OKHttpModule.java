package com.wx.daggerdemo.dagger.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class OKHttpModule {
    @Singleton
    @Provides
    OkHttpClient okHttpClientProvider() {
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
