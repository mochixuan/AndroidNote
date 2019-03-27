package com.wx.glidedemo1.widget.divider;

import android.content.Context;

public class DividerItemDecorationOne extends WDividerItemDecoration{


    public DividerItemDecorationOne(Context context) {
        super(context);
    }

    @Override
    public WDivider getDivider(int itemPosition) {
        WDivider wDivider = new WDivider(false, false, false, false,4, 0xfff0f0f0);
        wDivider.setBottom(true);
        return wDivider;
    }

}
