package com.wx.kotlindemo.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import com.wx.kotlindemo.R
import com.wx.kotlindemo.base.BaseActivity
import com.wx.kotlindemo.constant.DataConstant
import com.wx.kotlindemo.constant.NAME
import com.wx.kotlindemo.enums.Student
import com.wx.kotlindemo.utils.Manager
import kotlin.properties.Delegates

class EasyActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_easy
    }

    override fun initView() {

        (findViewById(R.id.btn_1) as Button).text = "常量"
        (findViewById(R.id.btn_1) as Button).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

            }
        })
        (findViewById(R.id.btn_1) as Button).setOnClickListener(View.OnClickListener {
            //-----引用常量-----
            System.out.println("=====================================>>"+ NAME) //不是对象的值
            System.out.println("=====================================>>"+ DataConstant.AGE)
            System.out.println("=====================================>>"+ DataConstant.SEX)
        })

        val student = Student("wang",22,12)
        student.age = 23
        val (_,age,_) = student
        System.out.println("===========>>"+age)
        //System.out.println("===========lazy>>"+mSimple)

        val arrays = IntArray(2).apply { fill(1) }

        System.out.println("===========>>${arrays.get(0)} ${arrays.get(1)}")

        //扩展函数
        student.learning("大家好")

        Manager.Toast()

        //Convariant3(EasyActivity::class.java)

        var name: String by Delegates.observable("wang") {
            property, oldValue, newValue ->
            System.out.println("========Delegates>>"+property.toString()+"  "+oldValue+"   "+newValue)
        }

        name = "mo"
        name = "chi"

        //EasyActivity@this
        //this@EasyActivity
        val s: Int = 1
        val s1: Int = 1
        val s2 = arrayOf(1,2,3)
        System.out.println("======s>>"+s.unaryPlus()+"   "+s+"   "+s1.plus(10)+"  "+s2.plus(99)[3]+"  "+s2.size)
        System.out.println("======s>>"+s.inc()+"    "+s)

        var a1: ArrayList<String>? = null
        System.out.println("======a1>>"+a1?.size)

        //val file = File("")
        //val ips = FileInputStream(file)

    }

    override fun initData() {
        Convariant1(EasyActivity::class.java) //
        Convariant2(Activity::class.java)
    }

    fun Convariant1(aClass: Class<out Activity>) {

    }

    fun Convariant2(aClass: Class<in Activity>) {

    }

    fun <T : Class<out Activity>> Convariant3(t: T) {
        startActivity(Intent(this,t))
    }

    //扩展函数
    fun Student.learning(course: String) {
        System.out.println("Learning ${course}")
    }


    //懒加载
    val mSimple: Int by lazy {
        System.out.println("===========lazy>>")
        return@lazy 1+2
    }

}
