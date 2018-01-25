package com.wx.viewpagermaster.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.databinding.FragemntOneBinding;

/**
 * Created by wangxuan on 2018/1/25.
 */

public class FragmentOne extends Fragment {

    public static FragmentOne newInstance(Bundle bundle) {
        FragmentOne fragmentOne  = new FragmentOne();
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String data = bundle.getString("data");
        FragemntOneBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragemnt_one,container,false);
        binding.tvView.setText(data);
        return binding.getRoot();
    }

}
