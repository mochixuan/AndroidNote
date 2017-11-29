package com.wx.fingerprintmaster;

import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.fingerprintmaster.fingertools.FingerManager;
import com.wx.fingerprintmaster.fingertools.OnFingerCallBackListenr;

public class MainActivity extends AppCompatActivity {

    private Button mStartFpm;
    private TextView mDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartFpm = findViewById(R.id.btn_start_fpm);
        mDetailView = findViewById(R.id.tv_detail);
        mStartFpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFpm();
            }
        });
    }

    private void startFpm() {

        mDetailView.setText("开始");

        FingerManager.getInstance().authenticate(this, mFingerCallBackListenr);

    }

    private OnFingerCallBackListenr mFingerCallBackListenr = new OnFingerCallBackListenr() {
        @Override
        public void onSupportFailed() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"设备不支持");
        }

        @Override
        public void onInsecurity() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"设备未设置安全模式");
        }

        @Override
        public void runException() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"运行时发生异常");
        }

        @Override
        public void onEnrollFailed() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"设备未添加指纹识别");
        }

        @Override
        public void onAuthenticationStart() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"开始识别，请将手指按下指纹识别器");
        }

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"指纹不匹配");
        }

        @Override
        public void onAuthenticationFailed() {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"采集或者认证的过程等错误");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"请再次输入指纹");
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            mDetailView.setText(mDetailView.getText().toString()+"\n"+"解锁成功");
        }
    };

    private void showToast(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

}
