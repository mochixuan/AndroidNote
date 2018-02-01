package com.wx.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.wx.kotlindemo.R
import com.wx.kotlindemo.interfaces.BindView
import com.wx.kotlindemo.interfaces.ImgType
import com.wx.kotlindemo.interfaces.LayoutId
import com.wx.kotlindemo.utils.Butterknife

@LayoutId(R.layout.activity_annotation)
class AnnotationActivity : AppCompatActivity() {

    @BindView(R.id.btn1)
    var mButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Butterknife.inject(this)
        mButton?.setOnClickListener {
            setImgType(1)
        }
    }

    fun setImgType(@ImgType type: Int) {

    }

}
