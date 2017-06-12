package com.wx.md.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.wx.md.R;
import com.wx.md.adapter.OneAdapter;
import com.wx.md.adapter.listener.OnItemClickListener;
import com.wx.md.base.BaseFragment;
import com.wx.md.databinding.FragmentTwoBinding;
import com.wx.md.model.DataBean;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class TwoFragment extends BaseFragment{

    private FragmentTwoBinding binding;
    private List<DataBean> mDataBeen;
    private OneAdapter mAdapter;

    private int[] imgIds = new int[]{R.mipmap.icon_chicken_1,R.mipmap.icon_fox_2,R.mipmap.icon_crab_3,R.mipmap.icon_koala_4,R.mipmap.icon_zebra_5,R.mipmap.icon_tiger_6};
    private String[] datas = new String[]{"小鸡","小狐","小虾","小考","小马","小虎"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (FragmentTwoBinding) binding;
    }

    @Override
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mAdapter = new OneAdapter();
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
        SlideInRightAnimator animator = new SlideInRightAnimator();
        animator.setAddDuration(400);
        binding.recyclerView.setItemAnimator(animator);
        mAdapter.addDatas(getData(),true);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    mAdapter.addDatas(getData(),true);
                }
            }
        });
    }

    private List<DataBean> getData() {
        if (mDataBeen == null) {
            mDataBeen = new ArrayList<DataBean>();
            for (int i = 0;i < imgIds.length;i++) {
                mDataBeen.add(new DataBean(imgIds[i],datas[i]));
            }
        }
        return mDataBeen;
    }

}
