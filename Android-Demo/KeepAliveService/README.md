# KeepAliveService## 0.进程分类>* 前台进程(Foreground process)>* 可见进程(Visible process)>* 服务进程(Service process)>* 后台进程(Background process)>* 空进程(Empty process)## 1.查看进程优先级>* adb shell 
>* 查看进程相关信息 ps|grep packagename
>* 查看进程的adj值 cat /proc/进程id/oom_adj## 2.Service常用种类>* Service基本的4.4下kill可以自动重启
>* JobService 4.4以上可以重启，但测试时很多不行
>* NotificationListenerService 4.4以上可以重启

## 3.粘性服务
>* 网上的资料: 在服务被杀死告诉系统怎么做，部分机型不行
>* START_STICKY
	>如果系统在onStartCommand返回后被销毁，系统将会重新创建服务并依次调用onCreate和onStartCommand（注意：根据测试Android2.3.3以下版本只会调用onCreate根本不会调用onStartCommand，Android4.0可以办到），这种相当于服务又重新启动恢复到之前的状态了）。

>* START_NOT_STICKY
	>如果系统在onStartCommand返回后被销毁，如果返回该值，则在执行完onStartCommand方法后如果Service被杀掉系统将不会重启该服务。
		
>* START_REDELIVER_INTENT
	>START_STICKY的兼容版本，不同的是其不保证服务被杀后一定能重启。

## 4. 方法
>* 1像素保活 可以提高进程的优先级
```
	1.可以实现息屏提高优先级，保护心跳
```
>* 项目保活 Notification方法
```
	1.Api<18 
		在服务中调用
		this.startForeground(ID, new Notification())

	2.APi>=18 模拟器是可以但我乐视手机(Android6.0)是不行的
		开启辅助类
		{
			Intent intent = new Intent(this,AssisService.class);
		    bindService(intent,mConnect,Service.BIND_AUTO_CREATE);
		}
		
		mConnect = {
			onServiceConnected{
				AssisService assisService = ((AssisService.LocalBinder)binder).getService();
				NotiService.this.startForeground(ID, getNotification());
				assisService.startForeground(ID, getNotification());
				assisService.stopForeground(true);
				NotiService.this.unbindService(mConnection);
				mConnection = null;
			}
			onServiceDisconnected{
			}
		}

		getNotification() {
			return NotificationCompat.Builder(this).build();
		}
```

5. 比较好的资料
>* [Android 进程保活招式大全](https://mp.weixin.qq.com/s?__biz=MzA3NTYzODYzMg==&mid=2653577617&idx=1&sn=623256a2ff94641036a6c9eea17baab8&scene=4#wechat_redirect)