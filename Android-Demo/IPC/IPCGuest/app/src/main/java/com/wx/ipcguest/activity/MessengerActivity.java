package com.wx.ipcguest.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.wx.ipcguest.R;
import com.wx.ipcguest.activity.base.BaseActivity;
import com.wx.ipcguest.databinding.ActivityMessengerBinding;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MessengerActivity extends BaseActivity {

    private ActivityMessengerBinding binding;
    private int a = 0;
    private Random rb = new Random();
    private Messenger mServiceMessenger;
    private ClientHandler mClientHandler = new ClientHandler(this);
    private Messenger mMessenger = new Messenger(mClientHandler);

    @Override
    public int getLayoutId() {
        return R.layout.activity_messenger;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMessengerBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.wx.ipcmaster.messenger");
                intent.setPackage("com.wx.ipcmaster");
                bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        binding.btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(mServiceConnection);
            }
        });
        binding.btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = 110;
                message.arg1 = ++a;
                int b = rb.nextInt(100);
                message.arg2 = b;
                message.replyTo = mMessenger;
                try {
                    mServiceMessenger.send(message);
                    TextView textView = new TextView(MessengerActivity.this);
                    textView.setId(a);
                    textView.setTextColor(Color.RED);
                    textView.setText(a + " + " + b+" = ");
                    binding.llContainer.addView(textView);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = 119;
                message.arg1 = ++a;
                int b = rb.nextInt(100);
                message.arg2 = b;
                message.replyTo = mMessenger;
                try {
                    mServiceMessenger.send(message);
                    TextView textView = new TextView(MessengerActivity.this);
                    textView.setId(a);
                    textView.setTextColor(Color.RED);
                    textView.setText(a + " + " + b+" = ");
                    binding.llContainer.addView(textView);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServiceMessenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void refreshResult(int id, int result) {
        TextView textView = binding.llContainer.findViewById(id);
        textView.setText(textView.getText()+" "+result);
    }


    static class ClientHandler extends Handler {

        private WeakReference<MessengerActivity> mActivity;

        public ClientHandler(MessengerActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() == null) return;
            switch (msg.what) {
                case 110:
                    mActivity.get().refreshResult(msg.arg1,msg.arg2);
                    break;
                case 119:
                    mActivity.get().refreshResult(msg.arg1,msg.arg2);
                    break;
            }
        }

    }



}
