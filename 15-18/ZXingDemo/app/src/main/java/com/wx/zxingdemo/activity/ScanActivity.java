package com.wx.zxingdemo.activity;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.zxingdemo.R;
import com.wx.zxingdemo.base.BaseActivity;
import com.wx.zxingdemo.databinding.ActivityScanBinding;

import cn.bingoogolapple.qrcode.core.QRCodeView;

public class ScanActivity extends BaseActivity{

    private ActivityScanBinding binding;

    @Override
    public int getLayout() {
        return R.layout.activity_scan;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityScanBinding) binding;
    }

    @Override
    public void initData() {
        //扫描结果
        binding.zxingView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                showToast("扫描成功:"+result);
            }

            @Override
            public void onScanQRCodeOpenCameraError() {
                showToast("扫描错误");
            }
        });
        //开灯
        binding.btnOpenLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.zxingView.openFlashlight();
            }
        });
        //关灯
        binding.btnCloseLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.zxingView.closeFlashlight();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.zxingView.startCamera();
        binding.zxingView.showScanRect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.zxingView.startSpot();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.zxingView.stopSpot();
    }

    @Override
    protected void onStop() {
        binding.zxingView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding.zxingView.onDestroy();
        super.onDestroy();
    }

}
