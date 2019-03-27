package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice7HsvEvaluatorView : View {

    private var mPaint = Paint()

    private var color: Int = 0xffff0000.toInt();

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    fun setColor(color: Int) {
        this.color = color
        invalidate()
    }

    fun getColor(): Int {
        return color
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = color
        canvas?.drawCircle(
                width/2f,
                height/2f,
                Math.min(width,height)/2f,
                mPaint
        )

    }

}
