package com.ppdl.md.activity.screen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityScreenOneBinding;

public class ScreenOneActivity extends BaseActivity {

    private ActivityScreenOneBinding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_screen_one;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityScreenOneBinding) binding;
    }

    @Override
    public void InitData() {
        binding.fabAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreenActivity();
            }
        });
    }

    public void openScreenActivity(){
        Intent intent = new Intent(this,JumpOneActivity.class);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            Transition transition = new ChangeTransform();
            transition.setDuration(6000);
            getWindow().setExitTransition(transition);  //退出动画

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create((View)binding.ivMiddle,"iv_scene_one"),
                    Pair.create((View)binding.fabAct,"fab_scene_one")
            );

            Bundle bundle = options.toBundle();
            startActivity(intent,bundle);

        } else {
            startActivity(intent);
        }
    }

}
