package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivitySheetBinding;

public class SheetActivity extends BaseActivity {

    private ActivitySheetBinding binding ;
    private BottomSheetBehavior behavior;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sheet;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivitySheetBinding) binding;
    }

    @Override
    public void InitData() {

        InitToolBar();

        behavior = BottomSheetBehavior.from(binding.nestScroll);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                /*-- 这里是bottom状态的改变 --*/
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                /*-- 这里是拖拽中的回调，根据slideoffset可以做一些动画 --*/
            }
        });


        binding.fabAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState();
            }
        });
    }

    private void InitToolBar() {
        binding.toolBar.setTitle("Material Design");
        setSupportActionBar(binding.toolBar);
    }

    private void changeState(){
        if (behavior.getState()==BottomSheetBehavior.STATE_HIDDEN){         //完全隐藏
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);         //最低高度
        }else if (behavior.getState()==BottomSheetBehavior.STATE_COLLAPSED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);          //完全展开
        } else if (behavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

}
