package com.wx.md;

import android.animation.Animator;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;

import com.wx.md.base.BaseActivity;
import com.wx.md.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (ActivityDetailBinding) binding;
    }

    @Override
    public void initData() {
        int res = getIntent().getIntExtra("img",-1);
        if (res>0) {
            binding.ivView.setBackgroundResource(res);
        }
        setInWindowAnimation();
        setExitWindowAnimation();
        binding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator1(binding.collLayout,R.color.tab_color,binding.fabButton);
            }
        });
    }

    public void setInWindowAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.TOP);
            slide.setDuration(500);
            getWindow().setEnterTransition(slide);
        }
    }

    public void setExitWindowAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(300);
            getWindow().setReturnTransition(fade);
        }
    }

    private void animatorFabOut() {
        binding.fabButton.animate()
                .setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in))
                .alpha(0)
                .scaleX(0)
                .scaleY(0);
    }

    private void animatorFabIn() {
        binding.fabButton.animate()
                .setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in))
                .alpha(1)
                .scaleX(1)
                .scaleY(1);
    }

    //动画1
    private void animator1(ViewGroup viewGroup, @ColorRes int color,View startView) {
        int cx = (startView.getLeft() + startView.getRight()) / 2;
        int cy = (startView.getTop() + startView.getBottom()) / 2;
        float finalRadius = (float) Math.hypot(viewGroup.getWidth(), viewGroup.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(viewGroup, cx, cy, 0, finalRadius);
        viewGroup.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

}
