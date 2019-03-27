package com.wx.ipcmaster.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.wx.ipcmaster.BookManager;
import com.wx.ipcmaster.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {

    public final String TAG = this.getClass().getSimpleName();

    private List<Book> mBooks = new ArrayList<>();

    private final BookManager.Stub mBookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                Log.w(TAG,"=====aidl master getBook: "+mBooks);
                if (mBooks != null)
                    return mBooks;
            }
            return new ArrayList<>();
        }

        @Override
        public void setBookPrice(Book book, int price) throws RemoteException {
            synchronized (this) {
                if (book == null) {
                    book = new Book();
                }
                book.setPrice(price);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.w(TAG,"=====aidl master setBookPrice: "+book.getName()+"  "+book.getPrice());
            }
        }

        @Override
        public void setBookName(Book book, String name) throws RemoteException {
            synchronized (this) {
                if (book == null) {
                    book = new Book();
                }
                book.setName(name);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.w(TAG,"=====aidl master setBookName: "+book.getName()+"  "+book.getPrice());
            }
        }

        @Override
        public void addBookIn(Book book) throws RemoteException {
            synchronized (this) {
                if (book == null) {
                    book = new Book();
                    book.setName("莫辞旋1");
                    book.setPrice(119);
                }
                book.setPrice(119);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.w(TAG,"=====aidl master addBookIn: "+book.getName()+"  "+book.getPrice());
            }
        }

        @Override
        public void addBookOut(Book book) throws RemoteException {
            synchronized (this) {
                if (book == null) {
                    book = new Book();
                    book.setName("莫辞旋2");
                    book.setPrice(110);
                }
                book.setPrice(110);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.w(TAG,"=====aidl master addBookIn: "+book.getName()+"  "+book.getPrice());
            }
        }

        @Override
        public void addBookInout(Book book) throws RemoteException {
            synchronized (this) {
                if (book == null) {
                    book = new Book();
                    book.setName("莫辞旋3");
                    book.setPrice(114);
                }
                book.setPrice(114);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.w(TAG,"=====aidl master addBookIn: "+book.getName()+"  "+book.getPrice());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG,"=====aidl master onCreate: ");
        Book book = new Book();
        book.setName("Java入门到精通");
        book.setPrice(79);
        mBooks.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.w(TAG,"=====aidl master onBind: ");
        return mBookManager;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w(TAG,"=====aidl master onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"=====aidl master onDestroy: ");
    }


}
