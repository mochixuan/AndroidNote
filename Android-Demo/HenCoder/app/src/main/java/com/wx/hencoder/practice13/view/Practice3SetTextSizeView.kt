package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice3SetTextSizeView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val text = "Hello MoChiXuan"

        mPaint.setTextSize(18f);
        canvas?.drawText(text, 100f, 25f, mPaint);
        mPaint.setTextSize(36f);
        canvas?.drawText(text, 100f, 70f, mPaint);
        mPaint.setTextSize(60f);
        canvas?.drawText(text, 100f, 145f, mPaint);
        mPaint.setTextSize(84f);
        canvas?.drawText(text, 100f, 240f, mPaint);
    }

}
