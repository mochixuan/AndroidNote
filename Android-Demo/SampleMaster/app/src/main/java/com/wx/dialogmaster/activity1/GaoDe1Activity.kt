package com.wx.dialogmaster.activity1

import android.databinding.ViewDataBinding
import android.view.View
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.bean.Student
import com.wx.dialogmaster.databinding.ActivityGaode1Binding

class GaoDe1Activity : BaseActivity() {

    private lateinit var binding: ActivityGaode1Binding

    override val layoutId: Int
        get() = R.layout.activity_gaode1

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityGaode1Binding
    }

    override fun initData() {
        binding.btn.setOnClickListener {
            //binding.viewStub.viewStub.inflate()
            binding.viewStub.viewStub.visibility = View.VISIBLE
        }
        val data = intent.getParcelableExtra<Student>("data")
        System.out.println("==========>>"+data.num+" ")
        System.out.println("==========>>"+data.user.age+" "+data.user.name)
        System.out.println("==========>>"+data.courses.get(0).date+" "+data.courses.get(0).name)

    }

}
