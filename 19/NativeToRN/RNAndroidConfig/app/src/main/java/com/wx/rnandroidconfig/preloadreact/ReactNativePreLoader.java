package com.wx.rnandroidconfig.preloadreact;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;

import java.util.HashMap;
import java.util.Map;

public class ReactNativePreLoader {

    private static final Map<String,ReactRootView> CACHE = new HashMap<>();

    public static void preLoad(Activity activity, String componentName) {
        if (CACHE.containsKey(componentName)) {
            return;
        }
        // 1.创建ReactRootView
        ReactRootView rootView = new ReactRootView(activity);
        rootView.startReactApplication(
                ((ReactApplication) activity.getApplication()).getReactNativeHost().getReactInstanceManager(),
                componentName,
                null);

        // 2.添加到缓存
        CACHE.put(componentName, rootView);
    }

    public static ReactRootView getReactRootView(String componentName) {
        return CACHE.get(componentName);
    }

    public static void deatchView(String component) {
        try {
            ReactRootView rootView = getReactRootView(component);
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            if (CACHE.containsKey(component)) {
                CACHE.remove(component);
            }
        } catch (Throwable e) {
            Log.e("ReactNativePreLoader",e.getMessage());
        }
    }
}
