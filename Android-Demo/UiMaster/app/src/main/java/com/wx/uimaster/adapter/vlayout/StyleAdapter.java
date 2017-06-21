package com.wx.uimaster.adapter.vlayout;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.wx.uimaster.R;
import com.wx.uimaster.adapter.viewholder.BaseViewHolder;
import com.wx.uimaster.databinding.ItemActionBinding;
import com.wx.uimaster.databinding.ItemBannerBinding;
import com.wx.uimaster.databinding.ItemCustomVlayoutBinding;
import com.wx.uimaster.databinding.ItemHeadBinding;
import com.wx.uimaster.databinding.ItemListBinding;
import com.wx.uimaster.databinding.ItemNavBinding;
import com.wx.uimaster.databinding.ItemRecommendBinding;
import com.wx.uimaster.model.ModelThree;
import com.wx.uimaster.widget.banner.MViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

//可能应为版本问题kotlin下会出错，还没有找到解决办法
public class StyleAdapter extends VirtualLayoutAdapter<BaseViewHolder>{

    private List<ModelThree> mResults;

    private final int BANNER = 101;
    private final int NAV = 102;
    private final int RECOMMEND_HEAD = 103;
    private final int RECOMMEND = 104;
    private final int ACTION_HEAD = 105;
    private final int ACTION = 106;
    private final int HOT_HEAD = 107;
    private final int HOT = 108;
    private final int LIST_HEAD = 109;
    private final int LIST = 110;

    public StyleAdapter(@NonNull VirtualLayoutManager layoutManager,List<ModelThree> results) {
        super(layoutManager);
        this.mResults = results;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == BANNER) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_banner,parent,false);
        } else if (viewType == NAV) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_nav,parent,false);
        } else if (viewType == RECOMMEND_HEAD || viewType == ACTION_HEAD || viewType == HOT_HEAD || viewType == LIST_HEAD) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_head,parent,false);
        } else if (viewType == RECOMMEND) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recommend,parent,false);
        } else if (viewType == ACTION) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_action,parent,false);
        } else if (viewType == HOT) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list,parent,false);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_custom_vlayout,parent,false);
        }
        BaseViewHolder viewHolder = new BaseViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (holder.getBinding() instanceof ItemBannerBinding) {
            ItemBannerBinding binding = (ItemBannerBinding) holder.getBinding();
            List<Integer> images = new ArrayList<>();
            for (int img : mResults.get(position).getIcon()) {
                images.add(img);
            }
            //如果是项目将开始StartTuring和结束stopTurning放在onresume和onpause里防止内存溢出，这里就不放了
            if (!binding.banner.isTurning()) {
                binding.banner.startTurning(3000);
            }

            binding.banner.setPages(new MViewHolderCreator(),images);
            binding.banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            binding.banner.setPageIndicator(new int[]{R.mipmap.icon_indicator_p,R.mipmap.icon_indicator_n});
        } else if (holder.getBinding() instanceof ItemNavBinding) {
            ItemNavBinding binding = (ItemNavBinding) holder.getBinding();
            binding.ivNav.setBackgroundResource(mResults.get(position).getIcon()[0]);
        } else if (holder.getBinding() instanceof ItemHeadBinding) {
            ItemHeadBinding binding = (ItemHeadBinding) holder.getBinding();
            binding.ivHead.setBackgroundResource(mResults.get(position).getIcon()[0]);
            binding.tvHead.setText(mResults.get(position).getTitle());
        } else if (holder.getBinding() instanceof ItemRecommendBinding) {
            ItemRecommendBinding binding = (ItemRecommendBinding) holder.getBinding();
            binding.tvRecommend.setText(mResults.get(position).getTitle());
            binding.ivRecommend.setBackgroundResource(mResults.get(position).getIcon()[0]);
        } else if (holder.getBinding() instanceof ItemActionBinding) {
            ItemActionBinding binding = (ItemActionBinding) holder.getBinding();
            binding.ivAction.setBackgroundResource(mResults.get(position).getIcon()[0]);
            binding.tvAction1.setText(mResults.get(position).getTitle());
            binding.tvAction2.setText(mResults.get(position).getContent());
        } else if (holder.getBinding() instanceof ItemListBinding) {
            ItemListBinding binding = (ItemListBinding) holder.getBinding();
            binding.ivList.setBackgroundResource(mResults.get(position).getIcon()[0]);
            binding.tvListTitle.setText(mResults.get(position).getTitle());
            binding.tvListContent.setText(mResults.get(position).getContent());
        } else if (holder.getBinding() instanceof ItemCustomVlayoutBinding) {
            ItemCustomVlayoutBinding binding = (ItemCustomVlayoutBinding) holder.getBinding();
            binding.tvCustom.setText(mResults.get(position).getTitle());
            ViewGroup.LayoutParams params = binding.ivCustom.getLayoutParams();
            params.height = mResults.get(position).getIcon()[1];
            binding.ivCustom.setLayoutParams(params);
            binding.ivCustom.setBackgroundResource(mResults.get(position).getIcon()[0]);
        }
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0) {
            return BANNER;
        } else if (position==1 || position == 2) {
            return NAV;
        } else if (position == 3) {
            return RECOMMEND_HEAD;
        } else if (position >= 4 && position <= 7) {
            return RECOMMEND;
        } else if (position == 8) {
            return ACTION_HEAD;
        } else if (position>=9 && position<=14) {
            return ACTION;
        } else if (position == 15) {
            return HOT_HEAD;
        } else if (position == 16 || position == 17) {
            return HOT;
        } else if (position == 18) {
            return LIST_HEAD;
        } else {
            return LIST;
        }

    }

}
