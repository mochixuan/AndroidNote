package com.ppdl.md.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityRevelBinding;

public class RevelAniActivity extends BaseActivity {

    private ActivityRevelBinding binding ;
    private Animator animator;
    private boolean isOpen=false;
    private int height;
    private int width;

    @Override
    public int getLayoutId() {
        return R.layout.activity_revel;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityRevelBinding) binding;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void InitData() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        binding.ivImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    animator = ViewAnimationUtils.createCircularReveal(
                            v,
                            v.getWidth() / 2,
                            v.getHeight() / 2,
                            v.getWidth(),
                            0
                    );
                    hide();
                } else {
                    animator = ViewAnimationUtils.createCircularReveal(
                            v,
                            v.getWidth() / 2,
                            v.getHeight() / 2,
                            0,
                            v.getWidth()
                    );
                    show();
                }
                animator.setInterpolator(new DecelerateInterpolator());
                animator.setDuration(1000);
                animator.start();
                isOpen = ! isOpen;
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void show(){

        View myView = binding.ivImg1;
        int cx = 0;
        int cy = 0;
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hide(){
        final View myView = binding.ivImg1;
        int cx = 0;
        int cy = 0;
        int initialRadius = myView.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

}
