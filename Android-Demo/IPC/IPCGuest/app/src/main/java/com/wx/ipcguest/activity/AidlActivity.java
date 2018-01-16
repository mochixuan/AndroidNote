package com.wx.ipcguest.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.wx.ipcguest.R;
import com.wx.ipcguest.activity.base.BaseActivity;
import com.wx.ipcguest.databinding.ActivityAidlBinding;
import com.wx.ipcmaster.BookManager;
import com.wx.ipcmaster.bean.Book;


public class AidlActivity extends BaseActivity {

    private ActivityAidlBinding binding;
    private BookManager mBookManager;

    public final String TAG = this.getClass().getSimpleName();
    private int bookId = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_aidl;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityAidlBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.wx.ipcmaster.aidl");
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
                try {
                    Book book = new Book();
                    book.setName("天塔"+(bookId++));
                    book.setPrice(bookId);
                    mBookManager.addBookIn(book);
                    Log.d(TAG,"=======addBookIn>"+book.getName()+"  "+book.getPrice());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Book book = new Book();
                    book.setName("活着"+(bookId++));
                    book.setPrice(bookId);
                    mBookManager.addBookOut(book);
                    Log.d(TAG,"=======addBookOut>"+book.getName()+"  "+book.getPrice());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.btnTest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Book book = new Book();
                    book.setName("开心就好"+(bookId++));
                    book.setPrice(bookId);
                    mBookManager.addBookInout(book);
                    Log.d(TAG,"=======addBookInout>"+book.getName()+"  "+book.getPrice());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBookManager = BookManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
