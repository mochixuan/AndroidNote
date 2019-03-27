package com.wx.okhttp.tool.cookies;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by wangxuan on 2018/1/20.
 */

public class CookiesManager implements CookieJar {

    private PersistentCookieStore mCookieStore;

    public CookiesManager(Context context) {
        mCookieStore = new PersistentCookieStore(context.getApplicationContext());
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                mCookieStore.add(url, cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return mCookieStore.get(url);
    }


}
