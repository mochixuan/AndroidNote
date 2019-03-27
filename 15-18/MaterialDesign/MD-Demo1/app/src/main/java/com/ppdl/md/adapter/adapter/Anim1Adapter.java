package com.ppdl.md.adapter.adapter;

import com.ppdl.md.R;
import com.ppdl.md.adapter.base.BaseAdapter;
import com.ppdl.md.adapter.viewholder.BaseViewHolder;
import com.ppdl.md.bean.SampleBean;
import com.ppdl.md.databinding.ItemTextBinding;

public class Anim1Adapter extends BaseAdapter<SampleBean>{

    @Override
    public int getLayouId() {
        return R.layout.item_text;
    }

    @Override
    public void onBindView(BaseViewHolder holder, SampleBean data) {
        ItemTextBinding binding = (ItemTextBinding) holder.getBinding();
        binding.ivView.setBackgroundResource(data.getIcon());
        binding.tvView.setText(data.getText());
    }

}
