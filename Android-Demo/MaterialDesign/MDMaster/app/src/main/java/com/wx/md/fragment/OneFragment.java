package com.wx.md.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wx.md.DetailActivity;
import com.wx.md.R;
import com.wx.md.adapter.OneAdapter;
import com.wx.md.adapter.listener.OnItemClickListener;
import com.wx.md.base.BaseFragment;
import com.wx.md.databinding.FragmentOneBinding;
import com.wx.md.model.DataBean;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class OneFragment extends BaseFragment{

    private FragmentOneBinding binding;
    private List<DataBean> mDataBeen;
    private OneAdapter mAdapter;

    private int[] imgIds = new int[]{R.mipmap.icon_chicken_1,R.mipmap.icon_fox_2,R.mipmap.icon_crab_3,R.mipmap.icon_koala_4,R.mipmap.icon_zebra_5,R.mipmap.icon_tiger_6};
    private String[] datas = new String[]{"小鸡","小狐","小虾","小考","小马","小虎"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (FragmentOneBinding) binding;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new OneAdapter();
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
        SlideInUpAnimator animator = new SlideInUpAnimator();
        animator.setAddDuration(400);
        binding.recyclerView.setItemAnimator(animator);
        mAdapter.addDatas(getData(),true);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    mAdapter.addDatas(getData(),true);
                } else {
                    Pair<View, String>[] pairs = new Pair[]{Pair.create(view.findViewById(R.id.iv_view),getString(R.string.tran_name))};
                    openActivity(DetailActivity.class,pairs,mAdapter.getDatas().get(position).getIcon());
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
