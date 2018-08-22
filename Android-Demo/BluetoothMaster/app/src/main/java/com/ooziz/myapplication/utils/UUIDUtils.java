package com.ooziz.myapplication.utils;

import java.util.UUID;

/**
 * Created by wangxuan on 2018/4/11.
 */

public class UUIDUtils {
    public static final String UUID_LOST_SERVICE = "0000fff0-0000-1000-8000-00805f9b34fb"; //getService通知
    public static final String UUID_LOST_WRITE = "0000fff1-0000-1000-8000-00805f9b34fb";   //getCharacteristic 写
    public static final String UUID_LOST_ENABLE = "0000fff2-0000-1000-8000-00805f9b34fb";  //getCharacteristic 通知
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb"; //getDescriptor 设置通知

    public UUIDUtils() {
    }
}
