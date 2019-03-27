package com.wx.dialogmaster.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.wx.dialogmaster.R;
import com.wx.dialogmaster.databinding.ItemSelectBinding;

/**
 * Created by wangxuan on 2018/1/19.
 */

public class SelectPopupWindow extends PopupWindow implements View.OnClickListener{

    public SelectPopupWindow(Context context) {
        LayoutInflater.from(context);
        ItemSelectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_select,null,false);
        setContentView(binding.getRoot());
        setFocusable(true);

    }

    @Override
    public void onClick(View view) {

    }

}
