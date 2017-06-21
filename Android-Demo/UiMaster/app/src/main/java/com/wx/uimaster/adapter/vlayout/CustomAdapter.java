package com.wx.uimaster.adapter.vlayout;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.wx.uimaster.R;
import com.wx.uimaster.adapter.viewholder.BaseViewHolder;
import com.wx.uimaster.databinding.ItemCustomVlayoutBinding;
import com.wx.uimaster.model.ModelThree;

import java.util.List;

//可能应为版本问题kotlin下会出错，还没有找到解决办法
public class CustomAdapter extends DelegateAdapter.Adapter<BaseViewHolder>{

    private List<ModelThree> mResults;
    private StaggeredGridLayoutHelper mLayoutHelper;

    public CustomAdapter(List<ModelThree> results, StaggeredGridLayoutHelper layoutHelper) {
        this.mResults = results;
        this.mLayoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_custom_vlayout,parent,false);
        BaseViewHolder viewHolder = new BaseViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ItemCustomVlayoutBinding binding = (ItemCustomVlayoutBinding) holder.getBinding();
        binding.tvCustom.setText(mResults.get(position).getTitle());

        ViewGroup.LayoutParams params = binding.ivCustom.getLayoutParams();
        params.height = mResults.get(position).getIcon()[1];
        binding.ivCustom.setLayoutParams(params);
        binding.ivCustom.setBackgroundResource(mResults.get(position).getIcon()[0]);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }
}
