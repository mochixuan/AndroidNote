package com.ppdl.md.activity.screen;

import android.databinding.Observable;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityScreentwoBinding;

public class ScreenTwoActivity extends BaseActivity {

    private ActivityScreentwoBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_screentwo;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityScreentwoBinding) binding;
    }

    @Override
    public void InitData() {


        /*binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                    ViewGroup viewGroup = (ViewGroup) binding.getRoot();
                    TransitionManager.beginDelayedTransition(viewGroup);
                    Toast.makeText(ScreenTwoActivity.this,"animator",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return true;
            }
        });*/

        binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {

            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ivImg1.setVisibility(binding.ivImg1.isShown()?View.GONE:View.VISIBLE);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ivImg2.setVisibility(binding.ivImg2.isShown()?View.GONE:View.VISIBLE);
            }
        });

    }

}
