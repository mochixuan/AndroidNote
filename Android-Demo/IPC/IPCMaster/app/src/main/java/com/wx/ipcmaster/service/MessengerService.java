package com.wx.ipcmaster.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public class MessengerService extends Service {

    private HandlerThread mHandlerThread ;
    private Handler mHandler;
    private Messenger mMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        mHandlerThread = new HandlerThread("messenger");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Messenger messenger = msg.replyTo;
                int arg1 = msg.arg1;
                int arg2 = msg.arg2;
                Message message = Message.obtain(null,msg.what,arg1,arg1+arg2);
                try {
                    switch (msg.what) {
                        case 110:
                            Thread.sleep(2000);
                            messenger.send(message);
                            break;
                        case 119:
                            messenger.send(message);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mMessenger = new Messenger(mHandler);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}
