package com.wx.glidedemo1.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 被 @GlideExtension 注解的类应以工具类的思维编写。
 * 这种类应该有一个私有的、空的构造方法，应为 final 类型，并且仅包含静态方法。
 * 被注解的类可以含有静态变量，可以引用其他的类或对象。
 * Created by wangxuan on 2018/1/25.
 */

@GlideExtension
public class MyGlideExtension {

    private MyGlideExtension() {

    }

    //修改全局得缓存设置
    @GlideOption
    public static void cacheSource(RequestOptions options) {
        options.diskCacheStrategy(DiskCacheStrategy.DATA);
    }

}
