package com.wx.glidedemo1.glide

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


/**
 * Created by wangxuan on 2018/2/2.
 */

object ImageBindingAdapter {

    @BindingAdapter("android:src")
    @JvmStatic
    fun setSrc(imageView: ImageView,srcIdRes: Int) {
        imageView.setImageResource(srcIdRes)
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, url: String) {
        val requestOptions = RequestOptions
                .bitmapTransform(RoundedCornersTransformation(10,0))

        GlideApp.with(imageView.context)
                .load(url)
                .fitCenter()
                .apply(requestOptions)
                .into(imageView)
    }

    @BindingAdapter("app:imageUrl","app:placeHolder")
    @JvmStatic
    fun loadImageFromUrl(
            imageView: ImageView,
            url: String,
            placeHolder: Drawable
    ) {

        GlideApp.with(imageView.context)
                .asBitmap()
                .load(url)
                .placeholder(placeHolder)
                .into(imageView)
    }

    @BindingAdapter("app:imageUrl","app:placeHolder","app:error")
    @JvmStatic
    fun loadImageFromUrl(
            imageView: ImageView,
            url: String,
            placeHolder: Drawable,
            error: Drawable
    ) {
        GlideApp.with(imageView.context)
                .load(url)
                .error(error)
                .placeholder(placeHolder)
                .into(imageView)
    }

}
