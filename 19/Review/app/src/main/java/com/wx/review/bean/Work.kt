package com.wx.review.bean

class Work(var name: String) {

    init {
        name = "your name ${name}"
    }

    constructor(name1: String,age1: Int): this(name1) {

    }

    fun liveWork() {

    }

}

class ITWork {

}
