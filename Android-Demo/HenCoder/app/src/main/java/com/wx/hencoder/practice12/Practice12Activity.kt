package com.wx.hencoder.practice12

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice12Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice12Activity : BaseActivity() {

    private var binding: ActivityPractice12Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice12

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice12Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice2_linergradient,R.string.LinearGradient))
        pager.add(PageModel(R.layout.practice2_radialgradient,R.string.RadialGradient))
        pager.add(PageModel(R.layout.practice2_sweepgradient,R.string.SweepGradient))
        pager.add(PageModel(R.layout.practice2_bitmapshader,R.string.BitmapShader))
        pager.add(PageModel(R.layout.practice2_composeshader,R.string.ComposeShader))
        pager.add(PageModel(R.layout.practice2_lightcolorfilter,R.string.LightingColorFilter))
        pager.add(PageModel(R.layout.practice2_colormatrixcolorfilter,R.string.ColorMatrixColorFilter))
        pager.add(PageModel(R.layout.practice2_xfermode,R.string.Xfermode))
        pager.add(PageModel(R.layout.practice2_strokecap,R.string.StrokeCap))
        pager.add(PageModel(R.layout.practice2_strokejoin,R.string.StrokeJoin))
        pager.add(PageModel(R.layout.practice2_strokemiter,R.string.StrokeMiter))
        pager.add(PageModel(R.layout.practice2_patheffect,R.string.PathEffect))
        pager.add(PageModel(R.layout.practice2_shadowlayer,R.string.ShadowLayer))
        pager.add(PageModel(R.layout.practice2_maskfilter,R.string.MaskFilter))
        pager.add(PageModel(R.layout.practice2_fillpath,R.string.FillPath))
        pager.add(PageModel(R.layout.practice2_textpath,R.string.TextPath))
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
