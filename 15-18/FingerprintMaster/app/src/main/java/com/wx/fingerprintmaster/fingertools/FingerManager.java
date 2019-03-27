package com.wx.fingerprintmaster.fingertools;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;

public class FingerManager {

    private static FingerManager INSTANCE;
    private CancellationSignal mCancellationSignal;

    public static FingerManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FingerManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FingerManager();
                }
            }
        }
        return INSTANCE;
    }

    public void authenticate(Context context, final OnFingerCallBackListenr listenr) {

        if (listenr == null) return;

        //6.0以下放弃防止不必要的问题出现
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            listenr.onSupportFailed();
            return;
        }

        try {

            FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(context);

            if (!managerCompat.isHardwareDetected()) {
                listenr.onSupportFailed();
            }

            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!keyguardManager.isKeyguardSecure()) {
                    listenr.onInsecurity();
                }
            }

            if (!managerCompat.hasEnrolledFingerprints()) {
                listenr.onEnrollFailed();
            }

            listenr.onAuthenticationStart();

            mCancellationSignal = new CancellationSignal();
            final CryptoObjectHelper objectHelper = new CryptoObjectHelper(context.getPackageName());
            final FingerprintManagerCompat.CryptoObject cryptoObject = objectHelper.buildCryptoObject();
            managerCompat.authenticate(cryptoObject, 0, mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    super.onAuthenticationError(errMsgId, errString);
                    listenr.onAuthenticationError(errMsgId,errString);
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    super.onAuthenticationHelp(helpMsgId, helpString);
                    listenr.onAuthenticationHelp(helpMsgId,helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    try {

                        /*if (result == null) {
                            Log.d("=====================","1");
                        } else if (result.getCryptoObject() == null){
                            Log.d("=====================","2");
                        } else if (result.getCryptoObject().getCipher() == null){
                            Log.d("=====================","3");
                        } else if (result.getCryptoObject().getCipher().doFinal() == null){
                            Log.d("=====================","4");
                        } else {
                            Log.d("=====================","5");
                        }*/

                        //result.getCryptoObject().getCipher().doFinal(objectHelper.GetKey().getEncoded());
                        listenr.onAuthenticationSucceeded(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        listenr.runException(); //数据可能被拦截了
                    }
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    listenr.onAuthenticationFailed();
                }
            },new Handler());

        } catch (Exception e) {
            e.printStackTrace();
            listenr.runException();
        }

    }

}
