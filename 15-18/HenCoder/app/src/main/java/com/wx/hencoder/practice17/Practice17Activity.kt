package com.wx.hencoder.practice17

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice17Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice17Activity : BaseActivity() {

    private var binding: ActivityPractice17Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice17

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice17Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice7_argbevaluatorlayout,R.string.argbEvaluator))
        pager.add(PageModel(R.layout.practice7_hsvevaluatorlayout,R.string.hsvEvaluator))
        pager.add(PageModel(R.layout.practice7_ofobjectlayout,R.string.ofObject))
        pager.add(PageModel(R.layout.practice7_propertyvaluesholderslayout,R.string.propertyValuesHolder))
        pager.add(PageModel(R.layout.practice7_animatorsetlayout,R.string.animatorSet))
        pager.add(PageModel(R.layout.practice7_keyframelayout,R.string.keyFrame))
        binding!!.viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return PageFragment(pager.get(position).layoutId)
            }

            override fun getCount(): Int {
                return pager.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pager.get(position).titleRes)
            }
        }
        binding!!.tabLayout.setupWithViewPager(binding?.viewPager)
    }

}
