package com.wx.glidedemo1.activity

import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.wx.glidedemo1.R
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.constant.Constants
import com.wx.glidedemo1.databinding.ActivityPractice1Binding
import com.wx.glidedemo1.glide.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation


class Practice1Activity : BaseActivity() {

    private lateinit var binding: ActivityPractice1Binding
    private val TAG = this.javaClass.simpleName

    override val layoutId: Int
        get() = R.layout.activity_practice1

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice1Binding
    }

    override fun initData() {

        binding.btn1.setOnClickListener {
            Glide.with(this)
                    .load(Constants.IMG1)
                    .into(binding.iv1)
        }

        binding.btn2.setOnClickListener {
            Glide.with(this)
                    .clear(binding.iv1)
        }

        /*
        * DiskCacheStrategy.NONE： 表示不缓存任何内容。
        * DiskCacheStrategy.DATA： 表示只缓存原始图片。
        * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
        * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
        * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
        * */

        //占位符
        val options1 = RequestOptions()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error)
                .override(400,200)  //加载成200*100像素的尺寸
                //.override(Target.SIZE_ORIGINAL) //加载原图
                //.diskCacheStrategy(DiskCacheStrategy.NONE) //禁止硬盘缓存 默认开启得
                .skipMemoryCache(true) //关闭内存缓存默认开启得
        Glide.with(this)
                //.asGif()         //指定类型
                //.asBitmap()      //指定类型
                //.asFile()        //强制指定文件格式的加载
                //.asDrawable()    //Drawable格式的加载
                //.load(Constants.IMG_GIT)  //Glide内部会自动判断图片格式
                //.load(Constants.IMG_ERROR)
                .load(Constants.IMG2)
                //.preload()       //预加载，先加载，用到得时候直接从缓存中读取
                .apply(options1)
                //.into(binding.iv2)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        //binding.iv2.setImageDrawable(resource)
                    }
                })

        Thread(Runnable {
            val target = Glide.with(Practice1Activity@this.applicationContext)
                                .asFile()
                                .load("http://www.guolin.tech/book.png")
                                //下载图片 下载原始尺寸的图片可以带宽高
                                .submit()
            //如果此时图片还没有下载完，那么get()方法就会阻塞住，一直等到图片下载完成才会有值返回。
            val imageFile = target.get()
            Log.d(TAG,"=========>>"+imageFile.path)
        }).start()

        /*Glide.with(this)
                .load(Constants.IMG5)
                .listener(object : RequestListener<Drawable> {
                    // 返回false就表示这个事件没有被处理，还会继续向下传递，返回true就表示这个事件已经被处理掉了，
                    // 从而不会再继续向下传递。举个简单点的例子，如果我们在RequestListener的onResourceReady()方法中返回了true，
                    // 那么就不会再回调Target的onResourceReady()方法了。
                    //返回true就不会进入into了
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Log.d(TAG,"=========onLoadFailed>>")
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        Log.d(TAG,"=========onResourceReady>>")
                        return false
                    }
                })
                .into(binding.iv2)*/

        //https://github.com/wasabeef/glide-transformations
        val options2 = RequestOptions()
                //.centerCrop()
                //.fitCenter()
                //.circleCrop()
                //模糊化和黑白化处理
                .transforms(BlurTransformation(),GrayscaleTransformation())
        //图片变换
        /*Glide.with(this)
                .load(Constants.IMG6)
                .apply(options2)
                .into(binding.iv2)*/

        //有可能你的IDE中会提供找不到GlideApp这个类。这个类是通过编译时注解自动生成的，首先确保你的代码中有一个自定义的模块，并且给它加上了@GlideModule注解
        GlideApp.with(this)
                .load(Constants.IMG1)
                .centerCrop()
                .cacheSource() //不错
                .into(binding.iv2)

    }



}
