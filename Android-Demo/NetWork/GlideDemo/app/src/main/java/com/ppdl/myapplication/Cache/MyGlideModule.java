package com.ppdl.myapplication.Cache;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

public class MyGlideModule implements GlideModule{

    private String BASE_PATH="/PPDL";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //设置缓存大小
        /*int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int memoryCacheSize=maxMemory/8;*/

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = (int)calculator.getMemoryCacheSize();

        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize));
        //builder.setBitmapPool( new LruBitmapPool( defaultBitmapPoolSize));

        //设置磁盘缓存大小
        if(SDUtils.isSDCardMounted()){
            int disCacheSize=1024*1024*50;
            String directoryPath = Environment.getExternalStorageDirectory().getPath();
            int freeSDSize=SDUtils.getFreeEXternalSize(directoryPath);
            if(freeSDSize!=0 && freeSDSize>50){
                if(SDUtils.setDirectory(directoryPath+BASE_PATH)){
                    builder.setDiskCache(new DiskLruCacheFactory(directoryPath+BASE_PATH,"CarFmImg",disCacheSize));//最大可以缓存多少数据
                }
            }
        }


        //设置缓存方式
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

        //设置BitMapPool缓存内存大小
        //builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));


    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

}
