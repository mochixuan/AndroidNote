package com.ppdl.myapplication.Cache;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.ppdl.myapplication.R;
import com.ppdl.myapplication.View.CircleImageView;
import com.ppdl.myapplication.View.GlideCircleTransform;

public class GlideUtils {

    private static GlideUtils glide;

    public static GlideUtils getInstance(){
        if(glide==null){
            synchronized (GlideUtils.class){
                if(glide==null){
                    glide=new GlideUtils();
                }
            }
        }
        return glide;
    }

    public void setImageView(Activity activity, final CircleImageView imageView, String imgUrl){

        Glide.with(activity).load(imgUrl)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.error)
                .override(240,240).
                into(new BitmapImageViewTarget(imageView){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        imageView.setImageBitmap(resource);
                    }
                });
    }

}
