package com.wx.rnandroidconfig.activity;

import com.facebook.react.ReactActivity;
import com.wx.rnandroidconfig.constant.BaseConstant;

import javax.annotation.Nullable;

public class ThreeActivity extends ReactActivity {

    @Nullable
    @Override
    protected String getMainComponentName() {
        return BaseConstant.RN_MC_NAME_THREE;
    }

}
