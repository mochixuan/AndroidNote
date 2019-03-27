package com.wx.kotlindemo.interfaces

interface MyInterface1 : MyInterface {

    override fun test1() {      //可从新写也可以不写

    }

    override fun test2() {
        super.test2()
    }

}
