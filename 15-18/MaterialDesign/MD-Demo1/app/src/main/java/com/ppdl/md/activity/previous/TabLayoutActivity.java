package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityTablayoutBinding;
import com.ppdl.md.fragment.MiFragment;

public class TabLayoutActivity extends BaseActivity {

    private ActivityTablayoutBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityTablayoutBinding) binding;
    }

   @Override
    public void InitData() {

       MiPageAdapter miPageAdapter =new MiPageAdapter(getSupportFragmentManager());
       binding.viewPager.setAdapter(miPageAdapter);
       binding.tabLayout.setupWithViewPager(binding.viewPager);
       binding.tabLayout.setSelectedTabIndicatorColor(Color.GREEN);

    }

    private final int tab_num = 3;

     class MiPageAdapter extends FragmentStatePagerAdapter{

         public MiPageAdapter(FragmentManager fm) {
             super(fm);
         }

         @Override
        public Fragment getItem(int position) {
            return MiFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return tab_num;
        }

         @Override
         public CharSequence getPageTitle(int position) {
             return "Tab"+position;
         }
     }

}
