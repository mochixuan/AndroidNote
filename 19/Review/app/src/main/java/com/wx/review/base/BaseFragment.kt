package com.wx.review.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

abstract class BaseFragment: android.support.v4.app.Fragment(),BaseView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,layoutId,container,false)
        setDataBinding(binding)
        initData()
        return binding.root
    }

    override fun openActivity(activity: Class<out Activity>) {
        startActivity(Intent(getActivity(),activity::class.java))
    }

    override fun showToast(data: String) {
        if (data === null) return
        Toast.makeText(context,data, Toast.LENGTH_SHORT).show()
    }

}