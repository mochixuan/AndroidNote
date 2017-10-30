package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3GetTextBoundsView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val text = "Hello MoChiXuan"
        mPaint.isFakeBoldText = true
        mPaint.textSize = DisplayUtil.dpTopx(context,24f)

        canvas?.drawText(text,140f,300f,mPaint)



        val fontMetrics = mPaint.fontMetrics
        val bounds = Rect()
        mPaint.getTextBounds(text,0,text.length,bounds)
        bounds.top = 300 - bounds.height()
        bounds.bottom = 300+6
        bounds.left = 140-6
        bounds.right = 140+mPaint.measureText(text).toInt()+6

        mPaint.strokeWidth = 12f
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        canvas?.drawRect(bounds,mPaint)


    }

}
