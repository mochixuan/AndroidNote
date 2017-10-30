package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3SetTextScaleXView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val text = "Hello MoChiXuan"
        mPaint.textSize = DisplayUtil.dpTopx(context,18f)

        canvas?.drawText(text,100f,100f,mPaint)

        mPaint.textScaleX = 1.6f
        canvas?.drawText(text,60f,200f,mPaint)

    }

}
