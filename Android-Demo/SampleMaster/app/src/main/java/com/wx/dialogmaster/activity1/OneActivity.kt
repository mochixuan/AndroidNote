package com.wx.dialogmaster.activity1

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.wx.dialogmaster.MainActivity
import com.wx.dialogmaster.R
import com.wx.dialogmaster.service.Test1Service
import java.io.IOException



class OneActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName
    private var mIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        Log.d(TAG,">>>>>>>>>>onCreate")

        findViewById<Button>(R.id.btn).setOnClickListener {
            val intent = Intent(this,TwoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn1).setOnClickListener {
            throw IOException()
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            beginForeService()
        }

        mIntent = Intent(this,Test1Service::class.java)
        startService(mIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,">>>>>>>>>>onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,">>>>>>>>>>onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,">>>>>>>>>>onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,">>>>>>>>>>onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,">>>>>>>>>>onStop")
    }

    override fun onDestroy() {
        stopService(mIntent)
        super.onDestroy()
        Log.d(TAG,">>>>>>>>>>onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,">>>>>>>>>>onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG,">>>>>>>>>>onRestoreInstanceState")
    }

    private fun beginForeService() {

        //创建点跳转的Intent(这个跳转是跳转到通知详情页)
        val intent = Intent(this, TwoActivity::class.java)
        //创建通知详情页的栈
        val stackBulider = TaskStackBuilder.create(this)
        //为其添加父栈 当从通知详情页回退时，将退到添加的父栈中
        stackBulider.addParentStack(MainActivity::class.java)
        stackBulider.addNextIntent(intent)

        val requestCode = SystemClock.uptimeMillis().toInt()
        Log.d(TAG,">>>>>>>>>>requestCode: "+requestCode)
        val pendingIntent = stackBulider.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("新消息")
                .setWhen(System.currentTimeMillis())
                .setOngoing(false)
                .setAutoCancel(true)  //自己维护通知的消失
                .setContentText("您有一条未读短信...")
                .setContentIntent(pendingIntent)  //设置跳转Intent到通知中
        //.setFullScreenIntent(pendingIntent,true); //将一个Notification变成悬挂式Notification

        //获取通知服务
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //构建通知
        val notification = mBuilder.build()
        //显示通知
        nm.notify(requestCode, notification)
        //启动前台服务
        //startForeground(0,notification);
    }

}
