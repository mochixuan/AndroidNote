package com.wx.kotlindemo.enums

enum class Person {
    NAME,AGE,SEX
}

data class Student(val name: String , var age: Int,val num: Int = 10)