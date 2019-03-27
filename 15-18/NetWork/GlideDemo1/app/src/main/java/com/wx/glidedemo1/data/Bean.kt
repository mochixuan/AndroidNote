package com.wx.glidedemo1.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import com.wx.glidedemo1.BR

/**
 * Created by wangxuan on 2018/1/24.
 */

/*data class ListBean (var firstImg: String) : BaseObservable() {
    constructor(firstImg: String,
                id: String,
                source: String,
                title: String,
                url: String,
                mark: String) : this(firstImg) {

    }
}*/

class ListBean() : BaseObservable() {
    constructor(firstImg: String,
                id: String,
                source: String,
                title: String,
                url: String,
                mark: String) : this() {
        this.firstImg = firstImg
        this.id  = id
        this.source = source
        this.title = title
        this.url = url
        this.mark = mark
    }
    var firstImg: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstImg)
        }
    var id: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }
    var source: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.source)
        }
    var title: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    var url: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }
    var mark: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.mark)
        }
}

data class ResultBean(
        val totalPage: Int,
        val ps: Int,
        val pno: Int,
        val list: List<ListBean>
)

data class WeChatBean(
        val reason: String,
        val result: ResultBean,
        val error_code: Int
)

data class Person(
        var edit1: String,
        var iv1: Int,
        var iv2: String,
        var name: String
)

class Person1() : BaseObservable() {

    constructor(edit1: String, iv1: Int, iv2: String, name: String) : this() {
        this.edit1 = edit1
        this.iv1 = iv1
        this.iv2 = iv2
        this.name = name
    }

    //自带双向通信
    var name1 = ObservableArrayList<String>()

    var edit1: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.edit1)
            //刷新所以
            //notifyChange()
        }
    var iv1: Int? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.iv1)
        }
    var iv2: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.iv2)
        }
    var name: String? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

}