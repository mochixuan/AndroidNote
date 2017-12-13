package com.wx.dialogmaster.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wx.dialogmaster.R;
import com.wx.dialogmaster.databinding.ItemFileBinding;
import com.wx.dialogmaster.model.FileBaseUiModel;
import com.wx.dialogmaster.model.FileDownListener;
import com.wx.dialogmaster.model.FileModel;

import java.util.List;

public class FileDownAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<FileModel> mFileModels;
    private Activity mActivity;
    private FileDownListener mFileDownListener;

    public FileDownAdapter(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_file,parent,false);
        BaseViewHolder viewHolder = new BaseViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        ItemFileBinding binding = (ItemFileBinding) holder.getBinding();
        FileModel fileModel = mFileModels.get(position);
        final FileBaseUiModel baseUiModel = fileModel.getUiModel();
        String tag  = fileModel.getDownModel().getFileUrl();
        Glide.with(mActivity).load(fileModel.getDownModel().getFileImg()).asBitmap().error(R.mipmap.ic_launcher).into(binding.ivImg);
        binding.tvTitle.setText(baseUiModel.getTitle());
        binding.tvDescri.setText(baseUiModel.getDescri());
        binding.progress.setProgress(baseUiModel.getProgress());
        binding.btn.setText(baseUiModel.getBtnState());
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFileDownListener != null) {
                    mFileDownListener.clickBtn(position);
                }
            }
        });

    }

    public void setData(List<FileModel> fileModels,boolean isRefresh) {
        this.mFileModels = fileModels;
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }
    public void addData(int position,FileModel fileModel,boolean isRefresh) {
        this.mFileModels.add(position,fileModel);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void setFileDownListener(FileDownListener listener) {
        this.mFileDownListener = listener;
    }

    @Override
    public int getItemCount() {
        return mFileModels.size();
    }

}
