package com.wx.uimaster

import android.databinding.ViewDataBinding
import android.view.View
import com.wx.uimaster.activity.ComplexRecyclerViewActivity
import com.wx.uimaster.activity.VCustomActivity
import com.wx.uimaster.activity.VStyleActivity

import com.wx.uimaster.base.BaseActivity
import com.wx.uimaster.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        binding.complexRecyclerview.setOnClickListener(this)
        binding.vLayoutCustom.setOnClickListener(this)
        binding.vLayoutStyle.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.complex_recyclerview -> openActivity(ComplexRecyclerViewActivity::class.java)
            R.id.v_layout_custom -> openActivity(VCustomActivity::class.java)
            R.id.v_layout_style -> openActivity(VStyleActivity::class.java)
        }
    }

}
