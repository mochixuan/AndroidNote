package com.wx.keepaliveservice.service

import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobSheduler : android.app.job.JobService(){

    val TAG = MyJobSheduler::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"==================================onCreate=")
        try {
            val builder = JobInfo.Builder(1, ComponentName(packageName, MyJobSheduler::class.java!!.getName()))
            builder.setPeriodic(500)  //500ms重启
            builder.setPersisted(true)
            val jobScheduler = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(builder.build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStartJob(p0: android.app.job.JobParameters?): Boolean {
        Log.d(TAG,"==================================onStartJob=")
        return false
    }

    override fun onStopJob(p0: android.app.job.JobParameters?): Boolean {
        Log.d(TAG,"==================================onStopJob=")
        return false
    }

}
