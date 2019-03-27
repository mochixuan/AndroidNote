package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.wx.hencoder.utils.DisplayUtil

class Practice5AfterDrawView : AppCompatImageView {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.textSize  = DisplayUtil.dpTopx(context!!,16f)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        mPaint.color = Color.parseColor("#f44336")
        canvas?.drawRect(0f,40f,200f,120f,mPaint)
        mPaint.color = Color.WHITE
        canvas?.drawText("New",20f,100f,mPaint)

    }

}
