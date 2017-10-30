package com.wx.hencoder.pratice14

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.wx.hencoder.R
import com.wx.hencoder.base.BaseActivity
import com.wx.hencoder.databinding.ActivityPractice14Binding
import com.wx.hencoder.fragment.PageFragment
import com.wx.hencoder.model.PageModel

class Practice14Activity : BaseActivity() {

    private var binding: ActivityPractice14Binding? = null

    override val layoutId: Int
        get() = R.layout.activity_practice14

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice14Binding
    }

    override fun initData() {
        var pager: ArrayList<PageModel> = arrayListOf()
        pager.add(PageModel(R.layout.practice4_cliprect,R.string.clipRect))
        pager.add(PageModel(R.layout.practice4_clippath,R.string.clipPath))
        pager.add(PageModel(R.layout.practice4_translate,R.string.translate))
        pager.add(PageModel(R.layout.practice4_scale,R.string.scale))
        pager.add(PageModel(R.layout.practice4_rotate,R.string.rotate))
        pager.add(PageModel(R.layout.practice4_skew,R.string.skew))
        pager.add(PageModel(R.layout.practice4_matrixtranslate,R.string.matrixTranslate))
        pager.add(PageModel(R.layout.practice4_matrixscale,R.string.matrixScale))
        pager.add(PageModel(R.layout.practice4_matrixrotate,R.string.matrixRotate))
        pager.add(PageModel(R.layout.practice4_matrixskew,R.string.matrixSkew))
        pager.add(PageModel(R.layout.practice4_camerarotate,R.string.cameraRotate))
        pager.add(PageModel(R.layout.practice4_camerarotatefixed,R.string.cameraRotateFixed))
        pager.add(PageModel(R.layout.practice4_camerarotatehittingface,R.string.cameraRotateHittingFace))
        pager.add(PageModel(R.layout.practice4_flipboard,R.string.flibboard))

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
