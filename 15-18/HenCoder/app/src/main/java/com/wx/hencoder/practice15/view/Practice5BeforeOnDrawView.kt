package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

class Practice5BeforeOnDrawView : AppCompatTextView {

    private var mPaint = Paint()
    private val bounds = RectF()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {

        mPaint.color = Color.parseColor("#FFC107")

        bounds.left = layout.getLineLeft(1)
        bounds.right = layout.getLineRight(1)
        bounds.top = layout.getLineTop(1).toFloat()
        bounds.bottom = layout.getLineBottom(1).toFloat()
        canvas?.drawRect(bounds,mPaint)

        bounds.left = layout.getLineLeft(3)
        bounds.right = layout.getLineRight(3)
        bounds.top = layout.getLineTop(3).toFloat()
        bounds.bottom = layout.getLineBottom(3).toFloat()
        canvas?.drawRect(bounds,mPaint)

        super.onDraw(canvas)
    }

}
