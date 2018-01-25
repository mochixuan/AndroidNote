package com.wx.viewpagermaster.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wx.viewpagermaster.R;
import com.wx.viewpagermaster.bean.PagerBean;
import com.wx.viewpagermaster.databinding.ItemView1Binding;

import java.util.List;

/**
 *
 * Created by wangxuan on 2018/1/25.
 */

public class MyPagerAdapter extends PagerAdapter{

    private List<PagerBean> mBeans;
    private LayoutInflater mInflater;

    private final String TAG = this.getClass().getSimpleName();

    public MyPagerAdapter(List<PagerBean> beans, Context context) {
        this.mBeans = beans;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ItemView1Binding) object).getRoot();
    }

    /**
     * 为给定的位置创建相应的View。创建View之后,需要在该方法中自行添加到container中。
     *
     * @param container ViewPager本身
     * @param position  给定的位置
     * @return 提交给ViewPager进行保存的实例对象
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //这行代码进行了显示 attachParent = true必须依附于container
        ItemView1Binding binding = DataBindingUtil.inflate(mInflater, R.layout.item_view1,container,true);
        binding.iv.setImageResource(mBeans.get(position).getIcon());
        binding.tv.setText(mBeans.get(position).getTitle());
        binding.llView.setBackgroundColor(mBeans.get(position).getColor());
        Log.d(TAG,"==============instantiateItem>>"+position);

        return binding;
    }

    /**
     * 为给定的位置移除相应的View。
     *
     * @param container ViewPager本身
     * @param position  给定的位置
     * @param object    在instantiateItem中提交给ViewPager进行保存的实例对象
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        ItemView1Binding binding = (ItemView1Binding) object;
        Log.w(TAG,"==============destroyItem>>"+position);
        container.removeView(binding.getRoot());
    }

    /**
     * ViewPager调用该方法来通知PageAdapter当前ViewPager显示的主要项,提供给用户对主要项进行操作的方法。
     *
     * @param container ViewPager本身
     * @param position  给定的位置
     * @param object    在instantiateItem中提交给ViewPager进行保存的实例对象
     *//*
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        //super.setPrimaryItem(container, position, object);
        //可以把赋值在这里显示
        ItemView1Binding binding = (ItemView1Binding) object;
        binding.llView.setBackgroundColor(mBeans.get(position).getColor());
        binding.iv.setImageResource(mBeans.get(position).getIcon());
        binding.tv.setText(mBeans.get(position).getTitle());
        Log.e(TAG,"==============setPrimaryItem>>"+position);
    }
*/
    /**
     * 新增方法,目前较多用于Design库中的TabLayout与ViewPager进行绑定时,提供显示的标题。
     *
     * @param position 给定的位置
     * @return 显示的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mBeans.get(position).getTag();
    }

}
