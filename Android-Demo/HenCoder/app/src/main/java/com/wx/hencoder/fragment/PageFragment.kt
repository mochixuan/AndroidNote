package com.wx.hencoder.fragment

import android.annotation.SuppressLint
import android.databinding.ViewDataBinding
import com.wx.hencoder.base.BaseFragment

@SuppressLint("ValidFragment")
class PageFragment(val layoutID: Int) : BaseFragment() {


    override val layoutId: Int
        get() = layoutID


    override fun setDataBinding(binding: ViewDataBinding) {

    }

    override fun initData() {

    }


}
