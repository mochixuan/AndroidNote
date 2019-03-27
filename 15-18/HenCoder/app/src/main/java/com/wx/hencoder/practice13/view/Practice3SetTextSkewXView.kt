package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3SetTextSkewXView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    /*
    * 设置文字横向错切角度。其实就是文字倾斜度的啦
    * */

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val text = "Hello MoChiXuan"
        mPaint.textSize = DisplayUtil.dpTopx(context,24f)

        canvas?.drawText(text,100f,100f,mPaint)

        mPaint.textSkewX = 0.5f
        canvas?.drawText(text,100f,200f,mPaint)

        mPaint.textSkewX = -0.5f
        canvas?.drawText(text,100f,300f,mPaint)

    }

}
