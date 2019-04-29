package com.wx.rnandroidconfig.activity;

import com.wx.rnandroidconfig.constant.BaseConstant;
import com.wx.rnandroidconfig.preloadreact.PreLoadReactActivity;

import javax.annotation.Nullable;

public class TwoActivity extends PreLoadReactActivity {

    @Nullable
    @Override
    protected String getMainComponentName() {
        return BaseConstant.RN_MC_NAME_TWO;
    }
}
