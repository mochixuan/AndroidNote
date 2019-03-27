package com.wx.crashlibrary.manager;

import android.os.Environment;

import com.wx.crashlibrary.utils.SDUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class CrashManager implements Thread.UncaughtExceptionHandler{

    private static CrashManager INSTANCE;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Builder mBuilder;

    public static CrashManager getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashManager();
                }
            }
        }
        return INSTANCE;
    }

    //在Application调用一次，之后Activity里不会重新赋值，防止内存泄露
    public Builder init() {
        if (mDefaultHandler == null) {
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        if (mBuilder == null) {
            mBuilder = new Builder();
        }
        return mBuilder;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (throwable != null && mBuilder != null) {
            mBuilder.analyError(throwable);         //分析错误
            mDefaultHandler.uncaughtException(thread,throwable);
        }
    }

    public static class Builder{

        private boolean isSaveSdcard = true;

        private String mPathDir ;
        private String mFileName;

        private void analyError(Throwable throwable) {
            if (isSaveSdcard) {
                StringBuilder builder = new StringBuilder();
                String errorTime = "";
                try {
                    Writer writer = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(writer);
                    throwable.printStackTrace(printWriter);
                    printWriter.close();
                    if (writer.toString() != null) {
                        builder.append(writer.toString());
                    }
                    writer.close();

                    long currentTimeMillis = System.currentTimeMillis();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    errorTime = format.format(currentTimeMillis);

                    if (mPathDir == null || mPathDir.equals("")) {
                        mPathDir = Environment.getExternalStorageDirectory().getPath()+"/crash/";
                    }

                    if (mFileName == null || mFileName.equals("")) {
                        mFileName = errorTime+"crash.log";
                    }

                    if (SDUtils.isSDCardMounted()) {
                        File file = new File(mPathDir);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(mPathDir + mFileName);
                        fos.write(builder.toString().getBytes());
                        fos.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Builder setSaveSdcard(boolean isSaveSdcard) {
            this.isSaveSdcard = isSaveSdcard;
            return this;
        }

        public Builder setPathDir(String dir) {
            if (!dir.endsWith("/")) {
                dir = dir+"/";
            }
            this.mPathDir = dir;
            return this;
        }

        public Builder setFileName(String fileName) {
            if (fileName.startsWith("/") && fileName.length()>1) {
                fileName = fileName.substring(1,fileName.length()-1);
            }
            this.mFileName = fileName;
            return this;
        }

        public void clearDefaultLog() {
            String defaultPath = Environment.getExternalStorageDirectory().getPath()+"/crash";
            SDUtils.deleteDirectory(defaultPath);
        }

        public void clearLog(String path) {
            SDUtils.deleteDirectory(path);
        }

    }



}
