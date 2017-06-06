package com.wx.zxingdemo.zxing.listener;

import android.graphics.Bitmap;

public interface onQREncoderListener {

    void encoderBitmap(boolean isSuccess, Bitmap bitmap);

}
