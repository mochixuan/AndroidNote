package com.wx.md.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.wx.md.R;
import com.wx.md.adapter.TwoAdapter;
import com.wx.md.adapter.listener.OnItemClickListener;
import com.wx.md.base.BaseFragment;
import com.wx.md.databinding.FragmentThreeBinding;
import com.wx.md.model.DataBean1;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

public class ThreeFragment extends BaseFragment{

    private FragmentThreeBinding binding;
    private List<DataBean1> mDataBeen;
    private TwoAdapter mAdapter;

    private int[] imgIds = new int[]{R.mipmap.m1,R.mipmap.m2,R.mipmap.m3,R.mipmap.m4,R.mipmap.m5,R.mipmap.m6,R.mipmap.m7,R.mipmap.m8};
    private String[] datas = new String[]{"小鸡","小狐","小虾","小考","小马","小虎","小虎","小虎"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (FragmentThreeBinding) binding;
    }

    @Override
    public void initData() {

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new TwoAdapter(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
        FadeInAnimator animator = new FadeInAnimator();
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

    private List<DataBean1> getData() {
        if (mDataBeen == null) {
            mDataBeen = new ArrayList<DataBean1>();
            int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            for (int i = 0;i < imgIds.length;i++) {
                mDataBeen.add(new DataBean1(imgIds[i],width/2,(int) (width/2 - Math.random() * 300),datas[i]));
            }
        }
        return mDataBeen;
    }


}
