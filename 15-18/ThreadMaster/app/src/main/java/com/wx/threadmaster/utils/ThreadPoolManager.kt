package com.wx.threadmaster.utils

import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by wangxuan on 2018/2/9.
 */

class ThreadPoolManager {

    companion object {
        private var INSTANCE: ThreadPoolManager? = null

        fun getInstance(): ThreadPoolManager {
            if (INSTANCE == null) {
                synchronized(ThreadPoolManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ThreadPoolManager()
                    }
                }
            }
            return INSTANCE!!
        }

    }

    private constructor() {

    }

    //别把顺序搞反了：http://upload-images.jianshu.io/upload_images/3985563-c05771982ad27f86.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
    //可以复写五，六，六，七四种构造函数
    private var mFourPool: ThreadPoolExecutor? = null
    fun base(thread: Thread) {
        if (mFourPool != null) {
            mFourPool = ThreadPoolExecutor(
                    2,
                    4,
                    10,
                    TimeUnit.SECONDS,
                    LinkedBlockingQueue()
            )
            mFourPool?.allowCoreThreadTimeOut(true)
        }
        if (!mFourPool!!.isShutdown) {
            mFourPool!!.execute(thread)
        }
    }

    private var mNewFixedPool: ExecutorService? = null
    fun newFixedThreadPool(thread: Thread) {
        if (mNewFixedPool == null) {

        }
    }

}