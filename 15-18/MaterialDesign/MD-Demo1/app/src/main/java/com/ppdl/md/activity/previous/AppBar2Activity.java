package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityAppBar2Binding;

//http://www.jianshu.com/p/06c0ae8d9a96

public class AppBar2Activity extends BaseActivity {

    private ActivityAppBar2Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_app_bar2;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityAppBar2Binding) binding;
    }

    @Override
    public void InitData() {

        binding.ivOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.appBar.setExpanded(true,true);
            }
        });

        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                // appBarLayout.getTotalScrollRange() 最大的值
                if (verticalOffset == 0) {      //完全展开

                } else if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange() ) {   //全隐藏
                    binding.llToolbar.setVisibility(View.VISIBLE);
                } else {
                    binding.llToolbar.setVisibility(View.GONE);
                }
            }
        });

    }

}
