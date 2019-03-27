package com.wx.kotlindemo.bean

data class Student(var name: String , var age: Int) {

    var person: com.wx.kotlindemo.bean.Person? = null

    inner class Person() {
        var sex: String? = null

        var num: Int = 0
            get() = field
            set(value) {
                field = value
            }

    }

    inner class a{

    }

    internal class b {

    }

}