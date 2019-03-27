package com.wx.kotlindemo.bean

class Person(private var name : String) {

    private var age : Int = 0

    init {          //可以在这里初始化
        name = "wang xuan"
    }

    constructor(name: String,age : Int) : this(name) {
        this.age = age
    }

    internal fun getString() :String{
        return name+"  "+age
    }

}

class Person1(name: String, age: Int) : Human(name, age) {

    val second = name

    init {
        val second = name
    }

    init {
        val second1 = name
    }

    constructor(name: String, age: Int,id: Int) : this(name,age) {

    }

    fun sencond() {

    }

}

open class Human(
    name: String,
    age: Int
)

//final
open class BaseParent {

    val a = "mo"

    open fun v() {}
    fun v1() {}
}

class BaseChildren() : BaseParent() {
    override fun v() {
        super.v()
    }

    val b = super.v1()

}

open class A

open class B: A()

class C<in A,out B>() {

    fun getB() : com.wx.kotlindemo.bean.B {
        return B()
    }

    fun setA(a: A) {
        val a1 = a
        val b = B()
    }

}

fun copy(from: Array<out A>, to: Array<in A>) {
    for (i in from.indices)
        to[i] = from[i]



}
