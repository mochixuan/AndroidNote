package com.wx.hencoder.practice11

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice11Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice11Activity : BaseActivity() {

    private var binding: ActivityPractice11Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice11

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice11Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice1_colorview,R.string.drawColor))
        pager.add(PageModel(R.layout.practice1_circleview,R.string.drawCircle))
        pager.add(PageModel(R.layout.practice1_reactview,R.string.drawReact))
        pager.add(PageModel(R.layout.practice1_pointview,R.string.drawPoint))
        pager.add(PageModel(R.layout.practice1_ovalview,R.string.drawOval))
        pager.add(PageModel(R.layout.practice1_lineview,R.string.drawLine))
        pager.add(PageModel(R.layout.practice1_rountrectview,R.string.drawRoundReact))
        pager.add(PageModel(R.layout.practice1_arcview,R.string.drawArc))
        pager.add(PageModel(R.layout.practice1_pathview,R.string.drawPath))
        pager.add(PageModel(R.layout.practice1_bitmapview,R.string.drawBitmap))
        pager.add(PageModel(R.layout.practice1_histogramview,R.string.drawHistogram))
        pager.add(PageModel(R.layout.practice1_piechartview,R.string.drawPieChart))
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
