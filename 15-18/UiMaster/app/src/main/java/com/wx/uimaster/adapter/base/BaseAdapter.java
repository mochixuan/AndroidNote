package com.wx.uimaster.adapter.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wx.uimaster.adapter.listener.OnItemClickListener;
import com.wx.uimaster.adapter.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private OnItemClickListener mListener;

    private List<T> mDatas;

    public abstract int getLayouId();


    public abstract void onBindView(BaseViewHolder holder, T data);

    public BaseAdapter() {
        mDatas = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),getLayouId(),parent,false);
        BaseViewHolder viewHolder = new BaseViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(view,position);
                }
            });
        }
        onBindView(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addDatas(List<T> data,boolean isWantAnimator) {
        int start = mDatas.size();
        mDatas.addAll(data);
        int end = mDatas.size()-1;
        if (isWantAnimator) {
            notifyItemRangeInserted(start,end);
        } else {
            notifyDataSetChanged();
        }
    }

    public void addData(T data,boolean isWantAnimator) {
        mDatas.add(data);
        if (isWantAnimator) {
            notifyItemInserted(mDatas.size()-1);
        } else {
            notifyDataSetChanged();
        }
    }

}
