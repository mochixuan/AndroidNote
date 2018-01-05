package com.wx.dialogmaster.model;

/**
 * Created by wangxuan on 2018/1/5.
 */

public interface DefaultFactory {

    void getAge();

    default String getName() {
        return "mochixuan";
    }

    static int getAga() {
        return 22;
    }

}
