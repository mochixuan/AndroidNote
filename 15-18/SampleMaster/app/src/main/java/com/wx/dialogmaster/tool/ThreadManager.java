package com.wx.dialogmaster.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxuan on 2018/1/5.
 */

public class ThreadManager {


    public void threadpool() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        Thread thread = new Thread();

        ExecutorService service = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        service.execute(runnable);
        service.submit(runnable); //有返回值
        service.execute(thread);

        ExecutorService service1 = Executors.newFixedThreadPool(3);
        ExecutorService service2 = Executors.newCachedThreadPool();
    }

}
