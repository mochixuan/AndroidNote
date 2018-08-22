package com.ooziz.myapplication.adapter;

import com.clj.fastble.data.BleDevice;
import com.ooziz.myapplication.R;
import com.ooziz.myapplication.adapter.base.BaseAdapter;
import com.ooziz.myapplication.adapter.base.BaseViewHolder;
import com.ooziz.myapplication.databinding.ItemScannerBinding;
import com.ooziz.myapplication.modal.BleAdvertisedData;
import com.ooziz.myapplication.utils.BleUtil;

public class ScanAdapter extends BaseAdapter<BleDevice>{
    @Override
    public int getLayoutId() {
        return R.layout.item_scanner;
    }

    @Override
    public void onBindView(BaseViewHolder holder, BleDevice data, int position) {
        ItemScannerBinding binding = (ItemScannerBinding) holder.getBinding();
        binding.tvName.setText(data.getName());
        binding.tvState.setText(data.getMac());
        binding.tvRss.setText(data.getRssi()+"%");
    }
}
