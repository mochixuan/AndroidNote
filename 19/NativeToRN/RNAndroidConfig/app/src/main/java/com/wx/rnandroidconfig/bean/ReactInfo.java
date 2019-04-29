package com.wx.rnandroidconfig.bean;

import android.os.Bundle;

public class ReactInfo {

    private String mainComponentName;

    private Bundle initialProperties;

    public ReactInfo(String mainComponentName, Bundle initialProperties) {
        this.mainComponentName = mainComponentName;
        this.initialProperties = initialProperties;
    }

    public String getMainComponentName() {
        return mainComponentName;
    }

    public void setMainComponentName(String mainComponentName) {
        this.mainComponentName = mainComponentName;
    }

    public Bundle getInitialProperties() {
        return initialProperties;
    }

    public void setInitialProperties(Bundle initialProperties) {
        this.initialProperties = initialProperties;
    }
}
