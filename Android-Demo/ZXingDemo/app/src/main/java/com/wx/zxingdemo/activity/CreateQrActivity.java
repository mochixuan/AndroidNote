package com.wx.zxingdemo.activity;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;

import com.wx.zxingdemo.R;
import com.wx.zxingdemo.base.BaseActivity;
import com.wx.zxingdemo.databinding.ActivityCreateqrBinding;
import com.wx.zxingdemo.zxing.encoder.QRCodeEncoder;

public class CreateQrActivity extends BaseActivity {

    private ActivityCreateqrBinding binding;

    @Override
    public int getLayout() {
        return R.layout.activity_createqr;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityCreateqrBinding) binding;
    }

    @Override
    public void initData() {
        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = binding.edContent.getText().toString().trim();
                if (data==null || data.isEmpty()) {
                    showToast("请输入内容");
                } else {
                    generateQrCode(data);
                }
            }
       });
    }

    private void generateQrCode(String data) {
        Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bitmap = QRCodeEncoder.getInstance().createQRCoder(data,300, Color.parseColor("#ff0000"),Color.parseColor("#33ffff"),logo);
        binding.ivQrcode.setImageBitmap(bitmap);
    }

}
