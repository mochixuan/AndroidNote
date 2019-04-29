package com.wx.review.activity

import android.databinding.ViewDataBinding
import com.wx.review.R
import com.wx.review.base.BaseActivity
import com.wx.review.bean.Student
import com.wx.review.databinding.ActivityKotlinBinding


class KotlinActivity : BaseActivity() {

    private lateinit var binding: ActivityKotlinBinding

    override val layoutId: Int
        get() = R.layout.activity_kotlin

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityKotlinBinding
    }

    override fun initData() {
        val student = Student(null,"江农")
        println("=====student 控指针>>"+student.person?.name)
        println("=====studnet 类型判断>>"+(student is Student))

        //readOnly
        var items = listOf(1,2,3,"4",5)
        for (item in items){}
        // items[0] = 1

        val whenState: Any = "a"
        val test = when(whenState) {
            0 -> println("0")
            else -> println("1")
        }
        println("=======return when>>"+test)

        items.filter { it === 1 }
                .map { it.toString().toUpperCase() }
                .forEach {}

        val testMaps = mapOf<String,String>()
        for ((key,value) in testMaps) {}

        val lazyValue: String by lazy { "Wang" }

        var letNo = null
        letNo?.let {
            // 代码会执行到此处, 假如data不为null
        }

        val myTurtle = Turtle()
        with(myTurtle) { // 画一个 100 像素的正方形
            penDown()
            for(i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
            penUp()
        }

        wangxuan@ for (i in 0..3) {
            for (j in 0..3) {
                if (j == 1) continue@wangxuan
                println("========for>>"+i+" :: "+j)
            }
            println("===============")
        }

        kotlin.run {
            val myTurtle = "wang xuan"
            myTurtle
        }

        with(student.person) {
            this?.name = "wang xuan"
        }

        student.person?.run {
            name = "wang xuan"
        }

        student.person?.let {
            it.name = "wang xuan"
        }

    }

    class Turtle {
        fun penDown(){}
        fun penUp(){}
        fun turn(degrees: Double){}
        fun forward(pixels: Double){}
    }



}
