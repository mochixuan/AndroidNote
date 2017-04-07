package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivitySheetdialogBinding;

public class SheetDialogActivity extends BaseActivity {

    private ActivitySheetdialogBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sheetdialog;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivitySheetdialogBinding) binding;
    }

    @Override
    public void InitData() {

        binding.fabAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

    }

    private void show(){
        BottomSheetDialog sheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.item_sheet_dialog,null,false);
        sheetDialog.setContentView(view);
        sheetDialog.show();
    }

}
