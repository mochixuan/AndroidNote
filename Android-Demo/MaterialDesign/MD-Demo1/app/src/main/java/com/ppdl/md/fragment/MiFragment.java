package com.ppdl.md.fragment;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppdl.md.R;
import com.ppdl.md.databinding.FragmentMiBinding;

public class MiFragment extends Fragment{

    private FragmentMiBinding binding;
    private static final String TAB_POSITION="tab_position";

    public static MiFragment newInstance(int tabPosition){
        MiFragment miFragment = new MiFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_POSITION,tabPosition);
        miFragment.setArguments(bundle);
        return miFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mi,container,false);
        Bundle bundle = getArguments();
        int tabPosition = bundle.getInt(TAB_POSITION);
        setText("第"+tabPosition+"个");
        return binding.getRoot();
    }

    public void setText(String data){
        binding.tvTab.setText(data);
    }

}
