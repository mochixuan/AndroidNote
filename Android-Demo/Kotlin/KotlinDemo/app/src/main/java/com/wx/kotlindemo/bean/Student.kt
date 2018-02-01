package com.wx.kotlindemo.bean

data class Student(var name: String , var age: Int) {

    var person: com.wx.kotlindemo.bean.Person? = null

    inner class Person() {
        var sex: String? = null

        var num: Int
        get() {
            return num
        }
        set(value) {
            num = value
        }

    }

    inner class a{

    }

    internal class b {

    }

}