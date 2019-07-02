package com.wx.rnandroidconfig.react;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.wx.rnandroidconfig.activity.ThreeActivity;

import javax.annotation.Nonnull;

public class WReactPackage extends ReactContextBaseJavaModule{

    public WReactPackage(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "WxModule";
    }

    @ReactMethod
    public void openActivity(String activityName) {
        Intent intent = new Intent();
        if (activityName.equals("ThreePage")) {
            intent.setClass(getCurrentActivity(), ThreeActivity.class);
        }
        getCurrentActivity().startActivity(intent);
    }

}
