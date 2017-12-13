package com.wx.updatalibrary.manager;

/**
 * Created by wangxuan on 2017/12/13.
 */

public class DownManager {

    private static DownManager INSTANCE;

    public static DownManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DownManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DownManager();
                }
            }
        }
        return INSTANCE;
    }

    

}
