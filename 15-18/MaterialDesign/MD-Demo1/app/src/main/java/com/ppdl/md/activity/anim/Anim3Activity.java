package com.ppdl.md.activity.anim;

import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAnim3Binding;

public class Anim3Activity extends BaseActivity {

    private ActivityAnim3Binding binding;
    private int mBottom;
    private int childWidth;

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim3;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAnim3Binding) binding;
    }

    @Override
    public void InitData() {

        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int oneThree = appBarLayout.getTotalScrollRange()*2/5;

                if (-oneThree <= verticalOffset) {
                    float alpha = (oneThree+verticalOffset)/(oneThree*1.0f);
                    binding.tvDevice.setAlpha(alpha);
                    binding.textviewLayout.setAlpha(alpha);
                    binding.tvAddress.setAlpha(alpha);
                    binding.llChild.setVisibility(View.GONE);
                } else {
                    binding.llChild.setVisibility(View.VISIBLE);
                }
                float alpha = -verticalOffset*3/(2*appBarLayout.getTotalScrollRange()*1.0f);
                binding.llChild.setAlpha(alpha>=1?1:alpha);

            }
        });
    }

}
