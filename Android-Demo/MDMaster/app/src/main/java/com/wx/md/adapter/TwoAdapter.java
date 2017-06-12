package com.wx.md.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wx.md.R;
import com.wx.md.adapter.base.BaseAdapter;
import com.wx.md.adapter.viewholder.BaseViewHolder;
import com.wx.md.databinding.ItemList1Binding;
import com.wx.md.model.DataBean1;

public class TwoAdapter extends BaseAdapter<DataBean1> {

    private Activity activity;

    public TwoAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getLayouId() {
        return R.layout.item_list1;
    }

    @Override
    public void onBindView(BaseViewHolder holder, DataBean1 data) {
        ItemList1Binding binding = (ItemList1Binding) holder.getBinding();
        ViewGroup.LayoutParams params = binding.ivView.getLayoutParams();
        params.width = data.getWidth();
        params.height =  data.getHeight() ;
        binding.ivView.setLayoutParams(params);
        Glide.with(activity).load(data.getIcon()).asBitmap().into(binding.ivView);
    }

}
