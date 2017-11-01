package com.wx.hencoder.practice15

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice15Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice15Activity : BaseActivity() {

    private var binding: ActivityPractice15Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice15

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice15Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice5_afterondraw,R.string.afterOnDraw))
        pager.add(PageModel(R.layout.practice5_beforeondraw,R.string.beforeOnDraw))
        pager.add(PageModel(R.layout.practice5_ondrawlayout,R.string.onDrawLayout))
        pager.add(PageModel(R.layout.practice5_dispatchondrawlayout,R.string.dispatchDawLayout))
        pager.add(PageModel(R.layout.practice5_afterondrawforgound,R.string.afterOnDrawForeground))
        pager.add(PageModel(R.layout.practice5_beforeondrawforgound,R.string.beforeOnDrawForeground))
        pager.add(PageModel(R.layout.practice5_afterdraw,R.string.afterDraw))
        pager.add(PageModel(R.layout.practice5_beforedraw,R.string.beforeDraw))
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
