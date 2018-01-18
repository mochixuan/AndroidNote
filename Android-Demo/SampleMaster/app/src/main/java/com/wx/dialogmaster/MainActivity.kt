package com.wx.dialogmaster

import android.content.Intent
import android.databinding.ViewDataBinding
import com.wx.dialogmaster.activity.*
import com.wx.dialogmaster.activity1.GaoDe1Activity
import com.wx.dialogmaster.activity1.OneActivity
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.bean.Course
import com.wx.dialogmaster.bean.Student
import com.wx.dialogmaster.bean.User
import com.wx.dialogmaster.databinding.ActivityMainBinding
import com.wx.dialogmaster.fragment.FragmentTestActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding
    }

    override fun initData() {
        
        binding.btn1.setOnClickListener {
            openActivity(MaterialDesignActivity::class.java)
        }

        binding.btn2.setOnClickListener {
            openActivity(OkgoBaseActivity::class.java)
        }

        binding.btn3.setOnClickListener {
            openActivity(OkgoDownActivity::class.java)
        }

        binding.btn4.setOnClickListener {
            openActivity(Android7Activity::class.java)
        }

        binding.btn5.setOnClickListener {
            openActivity(OkHttpActivity::class.java)
        }

        binding.btn6.setOnClickListener {
            openActivity(KeyControllActivity::class.java)
        }

        binding.btn7.setOnClickListener {
            openActivity(GaodeActivity::class.java)
        }

        binding.btn8.setOnClickListener {
            openActivity(OneActivity::class.java)
        }

        binding.btn9.setOnClickListener {
            openActivity(FragmentTestActivity::class.java)
        }

        binding.btn10.setOnClickListener {
            val courses = ArrayList<Course>()
            courses.add(Course("语文","10:00"))
            courses.add(Course("数学","11:00"))
            courses.add(Course("英语","12:00"))
            val student = Student()
            student.num = "110"
            student.user = User("mochixuan",22)
            student.courses = courses
            val intent = Intent(this@MainActivity,GaoDe1Activity::class.java)
            intent.putExtra("data",student)
            startActivity(intent)
        }

    }

}
