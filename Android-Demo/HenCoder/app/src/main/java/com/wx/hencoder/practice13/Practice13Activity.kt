package com.wx.hencoder.practice13

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice13Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice13Activity : BaseActivity() {

    private var binding: ActivityPractice13Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice13

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice13Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice3_drawtext,R.string.drawText))
        pager.add(PageModel(R.layout.practice3_staticlayout,R.string.staticLayout))
        pager.add(PageModel(R.layout.practice3_settextsize,R.string.setTextSize))
        pager.add(PageModel(R.layout.practice3_settypeface,R.string.setTypeface))
        pager.add(PageModel(R.layout.practice3_setfakeboldtext,R.string.setfakeBoldText))
        pager.add(PageModel(R.layout.practice3_strikethrutext,R.string.setStrikeThruText))
        pager.add(PageModel(R.layout.practice3_setunderlinetext,R.string.setUnderlineText))
        pager.add(PageModel(R.layout.practice3_settextskewx,R.string.setTextSkewX))
        pager.add(PageModel(R.layout.practice3_settextscalex,R.string.setTextScaleX))
        pager.add(PageModel(R.layout.practice3_settextalign,R.string.setTextAlign))
        pager.add(PageModel(R.layout.practice3_getfontspacing,R.string.getFontSpacing))
        pager.add(PageModel(R.layout.practice3_measuretext,R.string.measureText))
        pager.add(PageModel(R.layout.practice3_gettextbounds,R.string.getTextBounds))
        pager.add(PageModel(R.layout.practice3_getfontmetrics,R.string.getFontMetrics))
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
