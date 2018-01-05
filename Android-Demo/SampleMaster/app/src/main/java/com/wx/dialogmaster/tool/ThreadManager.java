package com.wx.dialogmaster.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxuan on 2018/1/5.
 */

public class ThreadManager {


    public void threadpool() {
        ExecutorService service = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }

}
