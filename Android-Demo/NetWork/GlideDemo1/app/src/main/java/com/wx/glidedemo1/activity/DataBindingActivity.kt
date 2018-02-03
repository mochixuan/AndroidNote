package com.wx.glidedemo1.activity

import android.databinding.ViewDataBinding
import com.wx.glidedemo1.R
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.data.Person
import com.wx.glidedemo1.databinding.ActivityDataBindingBinding

class DataBindingActivity : BaseActivity() {

    private lateinit var binding: ActivityDataBindingBinding
    private lateinit var mPerson: Person

    override val layoutId: Int
        get() = R.layout.activity_data_binding

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityDataBindingBinding
    }

    override fun initData() {
        mPerson = Person(
                "请输入姓名",
                R.mipmap.error,
                "https://img1.mukewang.com/5a28dde90001f75203120312-100-100.jpg",
                "无"
        )
        //binding.setVariable(BR.person,person)
        binding.person = mPerson
        //必须得赋值
        binding.presenter = Presenter()
    }

    inner class Presenter {
        //参数必须一样
        fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mPerson.name = s.toString()
            binding.person = mPerson
        }
    }

}
