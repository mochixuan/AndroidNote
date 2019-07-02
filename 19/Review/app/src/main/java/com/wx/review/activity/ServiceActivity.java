package com.wx.review.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.wx.review.R;
import com.wx.review.base.BaseActivity;
import com.wx.review.databinding.ActivityServiceBinding;
import com.wx.review.service.TestService;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

public class ServiceActivity extends BaseActivity {

    private String TAG = this.getClass().getSimpleName();
    private ActivityServiceBinding binding;
    private WHandler mWHandler;

    static class WHandler extends Handler {
        WeakReference<Activity> activity;

        WHandler(Activity activity) {
            this.activity = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    public void setDataBinding(@NotNull ViewDataBinding binding) {
        this.binding = (ActivityServiceBinding) binding;
    }

    @Override
    public void initData() {
        mWHandler = new WHandler(this);

        final Intent intent = new Intent(ServiceActivity.this, TestService.class);

        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                TestService.WIBinder binder = (TestService.WIBinder) iBinder;
                Log.d(TAG,"=========>>>onServiceConnected::"+binder.name);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG,"=========>>>onServiceDisconnected");
            }

            @Override
            public void onBindingDied(ComponentName name) {
                Log.d(TAG,"=========>>>onBindingDied");
            }

            @Override
            public void onNullBinding(ComponentName name) {
                Log.d(TAG,"=========>>>onNullBinding");
            }
        };

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                Message message = new Message();
                message.what = 0;
                mWHandler.sendMessage(message);
            }
        });
        binding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });
        binding.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });
        binding.btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWHandler.removeCallbacksAndMessages(null);
    }

    class DownTask extends AsyncTask<Integer,Integer,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            return null;
        }
    }

}
