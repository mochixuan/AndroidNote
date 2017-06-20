package com.wx.uimaster.widget.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class MViewHolderCreator implements CBViewHolderCreator<LocalImageHolderView>{
    @Override
    public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
    }
}
