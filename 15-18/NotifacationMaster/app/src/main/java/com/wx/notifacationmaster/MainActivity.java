package com.wx.notifacationmaster;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.wx.notifacationmaster.activity.SecondActivity;
import com.wx.notifacationmaster.base.BaseActivity;
import com.wx.notifacationmaster.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        settingOverlay();

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification1();
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification2();
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(2);
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification3();
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification4();
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification5();
            }
        });
        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification6();
            }
        });
        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification7();
            }
        });
    }


    public void notification1() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.buffery)) //同上
                .setContentTitle("开发小组")
                .setContentText("明天去哪里吃饭")
                .setSubText("不去我下线了")
                .setContentInfo("在线等")
                .setOngoing(false)    //禁止滑动删除
                .setAutoCancel(true) //设置自动取消
                //.setShowWhen(false) //设置不显示时间
                ;
        notificationManager.notify(1,builder.build());
    }

    public void notification2() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setContentTitle("开发小组")
                .setContentText("明天去哪里吃饭")
                .setContentIntent(pendingIntent)
                ;
        notificationManager.notify(2,builder.build());
    }

    public void notification3() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setContentTitle("开发小组")
                .setContentText("明天去哪里吃饭")
                .setContentIntent(pendingIntent)
                //调用系统默认响铃,设置此属性后setSound()会无效
                .setDefaults(
                        NotificationCompat.DEFAULT_VIBRATE|
                                NotificationCompat.DEFAULT_SOUND|
                                NotificationCompat.DEFAULT_LIGHTS
                                //NotificationCompat.DEFAULT_ALL
                )
                .setLights(0xFF0000,3000,3000)
                ;
        notificationManager.notify(3,builder.build());
    }

    public void notification4() {
        Intent intent1 = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                .setBigContentTitle("BigTextStyle后")
                .bigText("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展1。" +
                        "百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展2。" +
                        "百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展3。" +
                        "百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展4。" +
                        "百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展5。")
                .setSummaryText("末尾只一行的文字内容");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setContentTitle("BigTextStyle前")
                .setContentText("BigTextStyle演示前示例")
                //.setFullScreenIntent(pendingIntent,true)
                .setStyle(bigTextStyle)
                .addAction(R.mipmap.monkey,"确定",pendingIntent1) //有些手机无效
                .addAction(R.mipmap.buffery,"取消",pendingIntent2)
                ;
        notificationManager.notify(4,builder.build());
    }

    public void notification5() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.buffery)) //同上
                .setContentTitle("下载中")
                //.setContentInfo("在线")
                .setOngoing(true)    //禁止滑动删除
                .setAutoCancel(true) //设置自动取消
                .setShowWhen(false)
                .setProgress(100,10,true)
                ;
        notificationManager.notify(5,builder.build());
    }

    public void notification6() {
        NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.meinv));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.buffery)) //同上
                .setContentTitle("开发小组")
                .setContentText("明天去哪里吃饭")
                .setStyle(pictureStyle)
                ;
        notificationManager.notify(6,builder.build());
    }

    public void notification7() {
        Observable.timer(8, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.monkey) //很多手机无效
                                .setContentTitle("开发小组")
                                .setContentText("明天去哪里吃饭")
                                .setContentIntent(pendingIntent)
                                .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                //.setFullScreenIntent(pendingIntent,true) //要去系统设置里面设置允许横幅
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                ;
                        notificationManager.notify(7,builder.build());
                    }
                });
    }

    private void settingOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (! Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requestCode == 10) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(MainActivity.this,"not granted",Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
