package com.wx.md.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.wx.md.R;
import com.wx.md.base.BaseFragment;
import com.wx.md.databinding.FragmentLeftBinding;

public class LeftFragment extends BaseFragment{

    private FragmentLeftBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_left;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (FragmentLeftBinding) binding;
    }

    @Override
    public void initData() {

    }


}
