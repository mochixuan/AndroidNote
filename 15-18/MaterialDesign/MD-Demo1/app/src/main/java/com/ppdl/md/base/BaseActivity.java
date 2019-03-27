package com.ppdl.md.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.Gravity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity{

    private ViewDataBinding binding;

    public abstract int getLayoutId();
    public abstract void InitBing(ViewDataBinding binding);
    public abstract void InitData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        InitBing(binding);
        InitData();
    }

    public void openActitivity(Class<? extends BaseActivity> otherActivity){
        Intent intent = new Intent(this,otherActivity);
        startActivity(intent);
    }

    public void showToast(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }


    public void setupSlideAnimations() {            //滑动效果
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.RIGHT);
            slideTransition.setDuration(500);
            getWindow().setReenterTransition(slideTransition);
            getWindow().setEnterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }

    public void setupFadeAnimations() {         //可以渐变
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(500);
            Visibility visibility = fade;
            getWindow().setEnterTransition(visibility);
        }
    }

    public void setExplodeAnimations() {        //聚合的意思
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            Transition transition = explode;
            getWindow().setReenterTransition(transition);
            getWindow().setExitTransition(transition);
        }
    }


}
