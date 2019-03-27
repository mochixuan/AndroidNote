package com.wx.dialogmaster.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangxuan on 2017/12/11.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private ViewDataBinding binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

}
