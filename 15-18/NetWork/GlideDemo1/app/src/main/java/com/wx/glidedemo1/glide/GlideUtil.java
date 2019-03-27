package com.wx.glidedemo1.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by wangxuan on 2018/1/24.
 */

public class GlideUtil {

    private static GlideUtil INSTANCE;

    public static GlideUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (GlideUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GlideUtil();
                }
            }
        }
        return INSTANCE;
    }

    public void setImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public void setImage(Context context, String url, ImageView imageView, RequestOptions options) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    //实际上，使用Glide在大多数情况下我们都是不需要指定图片大小的，因为Glide会自动根据ImageView的大小来决定图片的大小，以此保证图片不会占用过多的内存从而引发OOM。

}
