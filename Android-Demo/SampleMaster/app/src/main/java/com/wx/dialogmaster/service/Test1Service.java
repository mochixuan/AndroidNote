package com.wx.dialogmaster.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.wx.dialogmaster.MainActivity;
import com.wx.dialogmaster.R;
import com.wx.dialogmaster.activity1.TwoActivity;

/**
 * Created by wangxuan on 2018/1/8.
 */

public class Test1Service extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //beginForeService();
    }

    private void beginForeService() {

        //创建点跳转的Intent(这个跳转是跳转到通知详情页)
        Intent intent = new Intent(this,TwoActivity.class);
        //创建通知详情页的栈
        TaskStackBuilder stackBulider = TaskStackBuilder.create(this);
        //为其添加父栈 当从通知详情页回退时，将退到添加的父栈中
        stackBulider.addParentStack(MainActivity.class);
        stackBulider.addNextIntent(intent);
        PendingIntent pendingIntent = stackBulider.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("新消息")
                .setWhen(System.currentTimeMillis())
                .setOngoing(false)
                .setAutoCancel(true)  //自己维护通知的消失
                .setContentText("您有一条未读短信...")
                .setContentIntent(pendingIntent);  //设置跳转Intent到通知中
                //.setFullScreenIntent(pendingIntent,true); //将一个Notification变成悬挂式Notification

        //获取通知服务
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //构建通知
        Notification notification = mBuilder.build();
        //显示通知
        nm.notify(0,notification);
        //启动前台服务
        //startForeground(0,notification);
    }

}
