package com.wx.hencoder.practice16

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice16Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice16Activity : BaseActivity() {

    private var binding: ActivityPractice16Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice16

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice16Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice6_translation,R.string.translation))
        pager.add(PageModel(R.layout.practice6_rotation,R.string.rotation))
        pager.add(PageModel(R.layout.practice6_scale,R.string.Ascale))
        pager.add(PageModel(R.layout.practice6_alpha,R.string.alpha))
        pager.add(PageModel(R.layout.practice6_multiproperties,R.string.multiproperties))
        pager.add(PageModel(R.layout.practice6_duration,R.string.durtion))
        pager.add(PageModel(R.layout.practice6_interpolator,R.string.interpolator))
        pager.add(PageModel(R.layout.practice6_objectanimatorlayout,R.string.objectanimatorlayout))
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
