package com.wx.basemaster1.activity

import android.databinding.ViewDataBinding
import android.util.Log
import com.wx.basemaster1.R
import com.wx.basemaster1.base.BaseActivity
import com.wx.basemaster1.databinding.ActivityKotlinBinding
import com.wx.basemaster1.model.Person
import com.wx.basemaster1.model.Student

class KotlinActivity : BaseActivity() {

    private var binding: ActivityKotlinBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_kotlin

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityKotlinBinding
    }

    override fun initData() {
        binding!!.view1.setOnClickListener {
            var stu: Student = Student("wang",10)
            var person: Person = Person("xuan",true)
        }
    }

}
