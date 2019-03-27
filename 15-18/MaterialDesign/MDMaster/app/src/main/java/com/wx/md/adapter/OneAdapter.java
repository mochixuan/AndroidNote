package com.wx.md.adapter;

import com.wx.md.R;
import com.wx.md.adapter.base.BaseAdapter;
import com.wx.md.adapter.viewholder.BaseViewHolder;
import com.wx.md.databinding.ItemListBinding;
import com.wx.md.model.DataBean;

public class OneAdapter extends BaseAdapter<DataBean> {

    @Override
    public int getLayouId() {
        return R.layout.item_list;
    }

    @Override
    public void onBindView(BaseViewHolder holder, DataBean data) {
        ItemListBinding binding = (ItemListBinding) holder.getBinding();
        binding.ivView.setBackgroundResource(data.getIcon());
        binding.tvView.setText(data.getData());
    }

}
