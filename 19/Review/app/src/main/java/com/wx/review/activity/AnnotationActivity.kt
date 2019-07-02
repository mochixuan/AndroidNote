package com.wx.review.activity

import android.databinding.ViewDataBinding
import com.wx.review.R
import com.wx.review.base.BaseActivity
import com.wx.review.databinding.ActivityAnnotationBinding

class AnnotationActivity : BaseActivity() {

    private lateinit var binding: ActivityAnnotationBinding

    override val layoutId: Int
        get() = R.layout.activity_annotation

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityAnnotationBinding
    }

    override fun initData() {

    }

}
