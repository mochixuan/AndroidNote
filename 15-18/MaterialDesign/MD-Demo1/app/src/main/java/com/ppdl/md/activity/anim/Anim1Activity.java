package com.ppdl.md.activity.anim;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppdl.md.R;
import com.ppdl.md.adapter.adapter.Anim1Adapter;
import com.ppdl.md.adapter.listener.OnItemClickListener;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.bean.SampleBean;
import com.ppdl.md.databinding.ActivityAnim1Binding;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Anim1Activity extends BaseActivity {

    private ActivityAnim1Binding binding;

    private Anim1Adapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim1;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAnim1Binding) binding;
    }

    @Override
    public void InitData() {
        setupWindowAnimations();

        mAdapter = new Anim1Adapter();
        SlideInUpAnimator animator = new SlideInUpAnimator();
        animator.setAddDuration(600);
        binding.recyclerView.setItemAnimator(animator);

        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==0) {
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                        TextView textView = (TextView) view.findViewById(R.id.tv_view);
                        ImageView imageView = (ImageView) view.findViewById(R.id.iv_view);
                        Intent intent = new Intent(Anim1Activity.this,Anim2Activity.class);
                        Transition transition = new ChangeTransform();
                        transition.setDuration(300);
                        getWindow().setExitTransition(transition);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                Anim1Activity.this, new Pair<View, String>(textView,getString(R.string.text_anim12)),new Pair<View, String>(imageView,getString(R.string.fb_button_anim12))
                        );
                        Bundle bundle = options.toBundle();
                        startActivity(intent,bundle);
                    } else {
                        openActitivity(Anim2Activity.class);
                    }
                } else if (position==1) {
                    mAdapter.addDatas(getData(),true);
                } else if (position == 2) {
                    mAdapter.addDatas(getTwoData(),true);
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addDatas(getData(),true);
                    }
                });
            }
        }).start();

    }

    public List<SampleBean> getData() {
        List<SampleBean> sampleBeen = new ArrayList<>();
        sampleBeen.add(new SampleBean("开心",R.mipmap.icon_test7));
        sampleBeen.add(new SampleBean("快乐",R.mipmap.icon_test6));
        sampleBeen.add(new SampleBean("痛苦",R.mipmap.icon_test3));
        sampleBeen.add(new SampleBean("尴尬",R.mipmap.icon_test4));
        sampleBeen.add(new SampleBean("苦笑",R.mipmap.icon_test5));
        return sampleBeen;
    }

    public List<SampleBean> getTwoData() {
        List<SampleBean> sampleBeen = new ArrayList<>();
        sampleBeen.add(new SampleBean("尴尬",R.mipmap.icon_test4));
        sampleBeen.add(new SampleBean("苦笑",R.mipmap.icon_test5));
        return sampleBeen;
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.LEFT);
            slideTransition.setDuration(500);
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }

}
