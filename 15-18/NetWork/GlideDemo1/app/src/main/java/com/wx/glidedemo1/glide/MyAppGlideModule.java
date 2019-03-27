package com.wx.glidedemo1.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.InputStream;

/**
 * 忘记了可以看下
 * http://blog.csdn.net/guolin_blog/article/details/78179422
 * Glide的这些默认配置都非常科学且合理，使用的缓存算法也都是效率极高的，因此在绝大多数情况
 * 下我们并不需要去修改这些默认配置，这也是Glide用法能如此简洁的一个原因。
 * InternalCacheDiskCacheFactory和ExternalCacheDiskCacheFactory的默认硬盘缓存大小都是250M。
 * 也就是说，如果你的应用缓存的图片总大小超出了250M，那么Glide就会按照DiskLruCache算法的原则来清理缓存的图片。
 * Created by wangxuan on 2018/1/24.
 */

@GlideModule
public class MyAppGlideModule extends com.bumptech.glide.module.AppGlideModule{

    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;

    //更改Glide配置
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //super.applyOptions(context, builder);
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,DISK_CACHE_SIZE));

        //RequestOptions options = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);
        //builder.setDefaultRequestOptions(options);


    }

    //替换Glide组件
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        //super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory());
    }

}
