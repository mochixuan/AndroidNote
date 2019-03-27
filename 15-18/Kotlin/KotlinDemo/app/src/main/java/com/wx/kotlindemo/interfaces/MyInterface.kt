package com.wx.kotlindemo.interfaces


interface MyInterface {

    var name: String

    val getName: String
        get() = name

    fun test1()

    fun test2() {
        // test1() 可以写方法体
        print(name)
    }

}
