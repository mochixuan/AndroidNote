package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.TextView;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAppbar1Binding;

public class AppBarLayout1 extends BaseActivity {

    private ActivityAppbar1Binding binding ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appbar1;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAppbar1Binding) binding;
    }

    @Override
    public void InitData() {
        animatorToolBar(binding.tvText);
    }

    private void animatorToolBar(View view) {
        if (view != null && view instanceof TextView) {
            TextView title = (TextView) view;
            title.setAlpha(0f);
            title.setScaleX(0.8f);
            title.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .setStartDelay(300)
                    .setDuration(900)
                    .setInterpolator(new AnticipateInterpolator());
        }
    }

}
