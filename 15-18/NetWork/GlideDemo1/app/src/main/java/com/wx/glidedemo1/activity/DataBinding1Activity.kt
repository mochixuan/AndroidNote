package com.wx.glidedemo1.activity

import android.databinding.ViewDataBinding
import com.wx.glidedemo1.R
import com.wx.glidedemo1.base.BaseActivity
import com.wx.glidedemo1.data.Person1
import com.wx.glidedemo1.databinding.ActivityDataBinding1Binding

class DataBinding1Activity : BaseActivity() {

    private lateinit var binding: ActivityDataBinding1Binding
    private lateinit var mPerson: Person1

    override val layoutId: Int
        get() = R.layout.activity_data_binding1

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityDataBinding1Binding
    }

    override fun initData() {
        mPerson = Person1(
                "请输入姓名",
                R.mipmap.ic_launcher_round,
                "https://img1.mukewang.com/5a28dde90001f75203120312-100-100.jpg",
                "无"
        )

        binding.person1 = mPerson
        binding.presenter1 = Presenter()
        binding.btn1.setOnClickListener {
            if (binding.edit1.text.toString().length != 0) {
                mPerson.name1.add(binding.edit1.text.toString())
            }
        }
    }

    inner class Presenter {
        //参数必须一样
        fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mPerson.name = s.toString()

            //binding.person = mPerson
        }
    }

}
