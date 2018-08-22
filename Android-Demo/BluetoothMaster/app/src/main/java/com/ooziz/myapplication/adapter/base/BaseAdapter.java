package com.ooziz.myapplication.adapter.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooziz.myapplication.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{


    @LayoutRes
    public abstract int getLayoutId();

    public abstract void onBindView(BaseViewHolder holder,T data,int position);

    private OnItemClickListener mListener;
    private List<T> mData = new ArrayList();

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),getLayoutId(),parent, false);
        BaseViewHolder holder = new BaseViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        onBindView(holder,mData.get(position),position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,mData.get(position),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addDatas(List<T> datas,boolean isWantAnimator) {
        int start = mData.size();
        mData.addAll(datas);
        int end = mData.size() - 1;
        if (isWantAnimator) {
            notifyItemRangeInserted(start,end);
        } else {
            notifyDataSetChanged();
        }
    }

    public void addData(T data,boolean isWantAnimator) {
        mData.add(data);
        int end = mData.size() - 1;
        if (isWantAnimator) {
            notifyItemInserted(end);
        } else {
            notifyDataSetChanged();
        }
    }

    public void replaceAllData(List<T> datas,boolean isWantAnimator) {
        mData.clear();
        mData.addAll(datas);
        if (isWantAnimator) {
            notifyItemRangeInserted(0,mData.size()-1);
        } else {
            notifyDataSetChanged();
        }
    }

    public T getData(int position) {
        if (position>=0 && position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

}
