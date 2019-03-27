package com.wx.zxingdemo.zxing.encoder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.Map;

public class QRCodeEncoder {

    private volatile static QRCodeEncoder INSTANCE;

    private Map<EncodeHintType, Object> mHints;

    public static QRCodeEncoder getInstance() {
        if (INSTANCE==null) {
            synchronized (QRCodeEncoder.class) {
                if (INSTANCE==null) {
                    INSTANCE = new QRCodeEncoder();
                }
            }
        }
        return INSTANCE;
    }

    private Map<EncodeHintType, Object> getHints() {
        if (mHints==null) {
            mHints = new EnumMap<>(EncodeHintType.class);
            mHints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            mHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            mHints.put(EncodeHintType.MARGIN, 0);
        }
        return mHints;
    }


    public Bitmap createQRCoder(String data, int size) {
        return enCoderAsBitmap(data, size, Color.BLACK, Color.WHITE, null);
    }


    public Bitmap createQRCoder(String data, int size, int foregroundColor) {
        return enCoderAsBitmap(data, size, foregroundColor, Color.WHITE, null);
    }

    public  Bitmap createQRCoder(String data, int size, int foregroundColor, Bitmap logo) {
        return enCoderAsBitmap(data, size, foregroundColor, Color.WHITE, logo);
    }

    public Bitmap createQRCoder(String data, int size, int foregroundColor, int backgroundColor, Bitmap logo){
        return enCoderAsBitmap(data, size, foregroundColor, backgroundColor, logo);
    }

    //生成
    private Bitmap enCoderAsBitmap(String content, int size, int foregroundColor, int backgroundColor, Bitmap logo) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, getHints());
            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * size + x] = foregroundColor;
                    } else {
                        pixels[y * size + x] = backgroundColor;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
            return addLogoToQRCode(bitmap, logo);
        } catch (Exception e) {
            return null;
        }
    }

    //添加logo
    private Bitmap addLogoToQRCode(Bitmap src, Bitmap logo) {
        if (src == null || logo == null) {
            return src;
        }
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
        }
        return bitmap;
    }

}
