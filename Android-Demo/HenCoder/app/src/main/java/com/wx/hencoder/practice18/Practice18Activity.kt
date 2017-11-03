package com.wx.hencoder.practice18

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice18Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice18Activity : BaseActivity() {

    private var binding: ActivityPractice18Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice18

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice18Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice8_likeview,R.string.likes))
        pager.add(PageModel(R.layout.practice8_tapeview,R.string.tapeView))
        pager.add(PageModel(R.layout.practice8_xmmotionview,R.string.xmMotion))
        pager.add(PageModel(R.layout.practice8_fliboardview,R.string.flipBoard))

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
