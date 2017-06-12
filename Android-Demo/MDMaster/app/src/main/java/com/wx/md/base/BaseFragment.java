package com.wx.md.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment{

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void setDataBinding(@NonNull ViewDataBinding binding);

    public abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        setDataBinding(binding);
        initData();
        return binding.getRoot();
    }


    public void back(View view) {
        getActivity().finish();
    }

    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(),activity);
        startActivity(intent);
    }

    public void showToast(@NonNull String data) {
        if (data != null && !data.isEmpty()) Toast.makeText(getActivity(),data,Toast.LENGTH_SHORT).show();
    }


    public void openActivity(Class<? extends Activity> activity, Pair<View, String>[] pairs,int imgs) {
        Intent intent = new Intent(getActivity(),activity);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),pairs);
        intent.putExtra("img",imgs);
        startActivity(intent,compat.toBundle());
    }

}
