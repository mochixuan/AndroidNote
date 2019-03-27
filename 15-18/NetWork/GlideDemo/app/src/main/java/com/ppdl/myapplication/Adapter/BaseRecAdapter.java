package com.ppdl.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppdl.myapplication.Bean;
import com.ppdl.myapplication.Cache.GlideUtils;
import com.ppdl.myapplication.R;
import com.ppdl.myapplication.View.CircleImageView;

import java.util.List;

public class BaseRecAdapter extends RecyclerView.Adapter<BaseRecAdapter.BaseViewHolder>{

    private List<Bean> mList;
    private Context context;
    private LayoutInflater mInflater;
    private boolean hasFooterView;
    private boolean hasHeadView;
    private boolean startUpPull;
    private boolean startDownPull;

    public BaseRecAdapter(List<Bean> mList, Context context){
        this.mList = mList;
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder=new BaseViewHolder(mInflater.inflate(R.layout.item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.textView.setText(mList.get(position).getText());
        GlideUtils.getInstance().setImageView((Activity) context,holder.imageView,mList.get(position).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class BaseHeadHolder extends RecyclerView.ViewHolder{
        private TextView tvHeader;
        public BaseHeadHolder(View itemView) {
            super(itemView);
            tvHeader= (TextView) itemView.findViewById(R.id.tv_header);
        }

    }

    public class BaseFooterHolder extends RecyclerView.ViewHolder{
        private TextView tvFooter;
        public BaseFooterHolder(View itemView) {
            super(itemView);
            tvFooter= (TextView) itemView.findViewById(R.id.tv_footer);
        }

    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private CircleImageView imageView;
        public BaseViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_msg);
            this.imageView = (CircleImageView) itemView.findViewById(R.id.iv_img);
        }
    }


    /* 1. SCROLL_STATE_FLING，这个参数表示你手离开后ListView还在“飞”中
       2. SCROLL_STATE_SETTLING，这个参数表示ListView停下不动了
       3. SCROLL_STATE_DRAGGING，这个参数表示你手还在ListView上 */
    private LinearLayoutManager manager;
    public void SetFootORHead(RecyclerView recyclerView, RecyclerView.ViewHolder Header,RecyclerView.ViewHolder Footer){

        if((recyclerView == null) || (Header==null && Footer==null)) return;


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(manager==null){
                    manager= (LinearLayoutManager) recyclerView.getLayoutManager();
                }
                if(manager.findLastCompletelyVisibleItemPosition()==mList.size()){

                }else{

                }
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        break;
                }

                return false;
            }
        });

    }


}
