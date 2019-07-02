package com.wx.hencoder.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun setDataBinding(binding: ViewDataBinding)

    abstract fun initData()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = DataBindingUtil.inflate<ViewDataBinding>(inflater!!, layoutId, container, false)
        setDataBinding(binding)
        initData()
        return binding.root
    }

    fun openActivity(other: Class<out Activity>) {
        val intent = Intent(activity, other)
        startActivity(intent)
    }

    fun showToast(data: String) {
        Toast.makeText(activity, data, Toast.LENGTH_SHORT).show()
    }

}
