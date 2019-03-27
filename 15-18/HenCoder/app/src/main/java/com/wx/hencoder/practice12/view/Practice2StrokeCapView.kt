package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice2StrokeCapView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.strokeWidth = DisplayUtil.dpTopx(context,20f).toFloat()

        mPaint.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(
                DisplayUtil.dpTopx(context,40f).toFloat(),
                DisplayUtil.dpTopx(context,80f).toFloat(),
                DisplayUtil.dpTopx(context,260f).toFloat(),
                DisplayUtil.dpTopx(context,80f).toFloat(),
                mPaint
        )

        mPaint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(
                DisplayUtil.dpTopx(context,40f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,260f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat()
                ,mPaint
        )

        mPaint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(
                DisplayUtil.dpTopx(context,40f).toFloat(),
                DisplayUtil.dpTopx(context,220f).toFloat(),
                DisplayUtil.dpTopx(context,260f).toFloat(),
                DisplayUtil.dpTopx(context,220f).toFloat(),
                mPaint
        )
    }

}
