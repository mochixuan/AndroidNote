package com.wx.daggerdemo.adapter;
import android.content.Context;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wx.daggerdemo.R;
import com.wx.daggerdemo.bean.PersonBean;

public class PersonAdapter extends BaseQuickAdapter<PersonBean,BaseViewHolder> {

    private Context mContext;

    public PersonAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonBean item) {
        ImageView imageView = helper.getView(R.id.iv_avatar);
        Glide.with(this.mContext).load(item.getImage()).into(imageView);
        ((TextView) helper.getView(R.id.tv_name)).setText(item.getName());
        ((TextView) helper.getView(R.id.tv_desc)).setText(item.getDesc());
    }
}
