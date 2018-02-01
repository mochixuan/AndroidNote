package com.wx.kotlindemo.activity

import android.util.Log
import android.view.View
import android.widget.Button
import com.wx.kotlindemo.R
import com.wx.kotlindemo.base.BaseActivity
import com.wx.kotlindemo.utils.StringUtils

class GrammarActivity : BaseActivity() {

    //-----局部变量不可变-----
    val wang1: Int = 1
    val wang2 = "wang2"     //自动识别

    private val TAG = this.javaClass.simpleName

    override fun getLayoutId(): Int {
        return R.layout.activity_grammar;
    }

    override fun initView() {
        (findViewById(R.id.btn_1) as Button).text = "简单函数"
        (findViewById(R.id.btn_1) as Button).setOnClickListener(View.OnClickListener {
            (findViewById(R.id.btn_1) as Button).text = "简单函数: " + sum2(1, 2)
        })

        (findViewById(R.id.btn_2) as Button).text = "简单输出"
        (findViewById(R.id.btn_2) as Button).setOnClickListener(View.OnClickListener {
            printSum(3,4)
        })

        (findViewById(R.id.btn_3) as Button).text = "类型检测"
        (findViewById(R.id.btn_3) as Button).setOnClickListener(View.OnClickListener {
            showToast(checkType(100))
            showToast(checkType("wang"))
        })

        (findViewById(R.id.btn_4) as Button).text = "for循环"
        (findViewById(R.id.btn_4) as Button).setOnClickListener(View.OnClickListener {
            testFor()
        })

        (findViewById(R.id.btn_5) as Button).text = "In 用法"
        (findViewById(R.id.btn_5) as Button).setOnClickListener(View.OnClickListener {
            testIn()
        })

        (findViewById(R.id.btn_6) as Button).text = "初识集合(链式)"
        (findViewById(R.id.btn_6) as Button).setOnClickListener(View.OnClickListener {
            testList()
        })

        (findViewById(R.id.btn_7) as Button).text = "简单的类"
        (findViewById(R.id.btn_7) as Button).setOnClickListener(View.OnClickListener {
            testClass()
        })

        (findViewById(R.id.btn_8) as Button).text = "默认参数"
        (findViewById(R.id.btn_8) as Button).setOnClickListener(View.OnClickListener {
            defaultConfig()
            defaultConfig("xuan")
        })

        (findViewById(R.id.btn_9) as Button).text = "将函数作为参数"
        (findViewById(R.id.btn_9) as Button).setOnClickListener(View.OnClickListener {
            lock { defaultConfig() }
        })

        (findViewById(R.id.btn_10) as Button).text = "静态"
        (findViewById(R.id.btn_10) as Button).setOnClickListener(View.OnClickListener {
            testStatic()
        })

        (findViewById(R.id.btn_11) as Button).text = "可空"
        (findViewById(R.id.btn_11) as Button).setOnClickListener(View.OnClickListener {
            Log.d(TAG,"=========="+parseInt("12345"))
            Log.d(TAG,"=========="+parseInt("s12345"))
            //Log.d(TAG,"=========="+"s12345".toInt())
        })

        (findViewById(R.id.btn_12) as Button).text = "包级别函数"
        (findViewById(R.id.btn_12) as Button).setOnClickListener(View.OnClickListener {
            com.wx.kotlindemo.utils.testMethod(1,2)
        })

        (findViewById(R.id.btn_13) as Button).text = "try/catch"
        (findViewById(R.id.btn_13) as Button).setOnClickListener(View.OnClickListener {
            val result = try {
                1
            } catch (e: ArithmeticException) {
                2
            }
            val result1 = try {
                1/0
            } catch (e: ArithmeticException) {
                2
            }
            Log.d(TAG,"=========="+result+"  "+result1)
        })

    }

    override fun initData() {

    }

    //-----1.简单函数-----
    fun sum1(a: Int, b: Int): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int): Int = if (a > b) a-b else a+b

    //-----2.简单输出
    fun printSum(a: Int, b: Int) {
        println("wo shi sum $a + $b = ${a+b}")
        showToast("wo shi sum $a + $b = ${a+b}")
    }

    //-----3.类型检测-----
    fun checkType(data: Any): String? {
        if (data is String) return data.substring(0,data.length)
        return null
    }

    //-----4.for循环-----
    fun testFor() {
        val items = listOf("hai","mei","xue","jihe");
        for (item in items) {
            print(item+"    ");
        }
        println()
    }

    //-----5.in 运算符来检测某个数字是否在指定区间内-----
    fun testIn() {
        val x = 10;
        val y = 9;

        if (x in 1..y) {
            println("=========(x in 1..y)="+true)
        } else{
            println("=========(x in 1..y)="+false)
        }

        if (x in 1..y+1) {
            println("=========(x in 1..y+1)="+true)
        } else{
            println("=========(x in 1..y+1)="+false)
        }

        for (a in 1..10) {
            println("========(a in 1..10)="+a)
        }

        for (a in 1..10 step 2) {
            println("========(a in 1..10 step 2)="+a)
        }

        for (a in 10 downTo 3 ) {
            println("========(a in 10 downTo 3)="+a)
        }

    }

    //-----6.初识集合-----
    fun testList() {
        val data = listOf<String>("apple","banana","car","driver","average","advance")
        data.filter {
            it.startsWith("a")      //过滤
        }.sortedBy {
            it                      //顺序
        }.map {
            it.toUpperCase()        //变大写
        }.forEach {
            println("=========>>"+it)
        }
    }

    //-----7.简单的类 加data就相当于提示数据类型-----
    data class Person(val name:String,val age:Int) {

    }
    fun testClass() {
        val person = Person("wang",21)
        println("===========>>"+person.name+"   "+person.age)
        println("===========>>"+person.toString())
    }

    //-----8.默认参数-----
    fun defaultConfig(data : String = "wang") {
        System.out.println("====================>>"+data)
    }

    //-----9.将函数作为参数-----
    fun <T> lock(body : () -> T ) : T {
        return body()
    }

    //-----10.静态-----
    fun testStatic() {
        StringUtils.isEmpty(null)
        StringUtils.StringUtils1.isEmpty("wang")
        StringUtils.StringUtils1.name
    }

    //-----11. 返回可空-----
    fun parseInt(str: String) : Int? {
        return str.toIntOrNull()
    }

}

