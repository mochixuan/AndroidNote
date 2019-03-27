package com.wx.fingerprintmaster.fingertools;

import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

public interface OnFingerCallBackListenr {

    /**
     * 判断设备是否支持
     */
    void onSupportFailed();

    /**
     *  这个条件的意思是，你的设备必须是使用屏幕锁保护的，这个屏幕锁可以是password，PIN或者图案都行。为什么是这样呢？因为google原生的逻辑就是：想要使用指纹识别的话，必须首先使能屏幕锁才行，这个和android 5.0中的smart lock逻辑是一样的，这是因为google认为目前的指纹识别技术还是有不足之处，安全性还是不能和传统的方式比较的
     */
    void onInsecurity();

    /**
     * 封装时代码，运行时
     */
    void runException();

    /**
     * 在android 6.0中，普通app要想使用指纹识别功能的话，用户必须首先在setting中注册至少一个指纹才行，否则是不能使用的。
     */
    void onEnrollFailed();

    /**
     * 开始认证
     */
    void onAuthenticationStart();

    /**
     * 认证失败是指所有的信息都采集完整，并且没有任何异常，但是这个指纹和之前注册的指纹是不相符的
     * @param errMsgId
     * @param errString
     */
    void onAuthenticationError(int errMsgId, CharSequence errString);

    /**
     * 认证错误是指在采集或者认证的过程中出现了错误，比如指纹传感器工作异常等
     */
    void onAuthenticationFailed();

    /**
     * 出现了可以回复的异常才会调用的。什么是可以恢复的异常呢？一个常见的例子就是：手指移动太快，当我们把手指放到传感器上的时候，如果我们很快地将手指移走的话，那么指纹传感器可能只采集了部分的信息，因此认证会失败。但是这个错误是可以恢复的，因此只要提示用户再次按下指纹，并且不要太快移走就可以解决
     * @param helpMsgId
     * @param helpString
     */
    void onAuthenticationHelp(int helpMsgId, CharSequence helpString);

    /**
     * 认证成功
     * 如果我们上面在调用authenticate的时候，我们的CryptoObject不是null的话，那么我们在这个方法中可以通过AuthenticationResult来获得Cypher对象然后调用它的doFinal方法。doFinal方法会检查结果是不是会拦截或者篡改过，如果是的话会抛出一个异常。当我们发现这些异常的时候都应该将认证当做是失败来来处理，为了安全建议大家都这么做
     * @param result
     */
    void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result);

}
