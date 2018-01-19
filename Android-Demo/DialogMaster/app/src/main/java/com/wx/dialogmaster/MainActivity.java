package com.wx.dialogmaster;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wx.dialogmaster.base.BaseActivity;
import com.wx.dialogmaster.databinding.ActivityMainBinding;
import com.wx.dialogmaster.databinding.ItemSelectBinding;
import com.wx.dialogmaster.listener.OnItemClickListener;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationPopupWindow(MainActivity.this, binding.getRoot(), new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showToast("单机了: "+position);
                    }
                });
            }
        });
        binding.ivMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewDownPopupWindow(MainActivity.this, view, new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showToast("单机了: "+position);
                    }
                });
            }
        });
        binding.ivMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindowBG(MainActivity.this, view, new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showToast("单机了: "+position);
                    }
                });
            }
        });
    }

    private void showLocationPopupWindow(Context context,View parent, final OnItemClickListener listener) {
        ItemSelectBinding childBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_select,null,false);
        PopupWindow popupWindow = new PopupWindow(
                childBinding.getRoot(),
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        popupWindow.setContentView(childBinding.getRoot());
        if (listener != null) {
            childBinding.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(0);
                }
            });
            childBinding.btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(1);
                }
            });
            childBinding.btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(2);
                }
            });
        }
        popupWindow.showAtLocation(parent, Gravity.BOTTOM,0,0);
    }

    private void showViewDownPopupWindow(Context context,View view, final OnItemClickListener listener) {
        ItemSelectBinding childBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_select,null,false);
        PopupWindow popupWindow = new PopupWindow(
                childBinding.getRoot(),
                300,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                false //单机外面不退出
        );
        popupWindow.setContentView(childBinding.getRoot());
        if (listener != null) {
            childBinding.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(0);
                }
            });
            childBinding.btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(1);
                }
            });
            childBinding.btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(2);
                }
            });
        }
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view,-(300-view.getMeasuredWidth())/2,0);
    }

    private void showPopupWindowBG(Context context,View view, final OnItemClickListener listener) {
        ItemSelectBinding childBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_select,null,false);
        PopupWindow popupWindow = new PopupWindow(
                childBinding.getRoot(),
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                true //单机外面不退出
        );
        popupWindow.setContentView(childBinding.getRoot());
        if (listener != null) {
            childBinding.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(0);
                }
            });
            childBinding.btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(1);
                }
            });
            childBinding.btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(2);
                }
            });
        }
        ColorDrawable colorDrawable = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.setAnimationStyle(R.style.PopUpTheme);
        popupWindow.showAsDropDown(view,-(300-view.getMeasuredWidth())/2,0);
    }

}
