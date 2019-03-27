package com.wx.viewpagermaster.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * FragmentStatePagerAdapter中fragment实例在destroyItem的时候被真正释放，
 * 所以FragmentStatePagerAdapter省内存。FragmentPagerAdapter中的fragment实例在destroyItem的时候并没有真正释放fragment对象只是detach，
 * 所以FragmentPagerAdapter消耗更多的内存，带来的好处就是效率更高一些。
 * 所以得出这样的结论：FragmentPagerAdapter适用于页面比较少的情况，FragmentStatePagerAdapter适用于页面比较多的情况，
 * 因此不同的场合选择合适的适配器才是正确的做法
 *
 * 相比通用的 PagerAdapter，该类更专注于每一页均为 Fragment 的情况。
 * 如文档所述，该类内的每一个生成的 Fragment 都将保存在内存之中，
 * 因此适用于那些相对静态的页，数量也比较少的那种；如果需要处理有很多页，
 * 并且数据动态性较大、占用内存较多的情况，应该使用FragmentStatePagerAdapter。
 * Created by wangxuan on 2018/1/25.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mLists;

    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> lists) {
        super(fm);
        this.mLists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }


}
