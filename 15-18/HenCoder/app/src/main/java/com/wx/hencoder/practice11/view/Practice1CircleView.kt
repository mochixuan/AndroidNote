package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1CircleView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ff99ffff")
        canvas?.drawCircle(DisplayUtil.dpTopx(context,80f),DisplayUtil.dpTopx(context,80f),DisplayUtil.dpTopx(context,50f),mPaint)

        mPaint.color = Color.parseColor("#ff9999ff")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 8f
        canvas?.drawCircle(DisplayUtil.dpTopx(context,220f),DisplayUtil.dpTopx(context,80f),DisplayUtil.dpTopx(context,50f),mPaint)

        mPaint.color = Color.parseColor("#ff9900ff")
        mPaint.strokeWidth = 20f
        canvas?.drawCircle(DisplayUtil.dpTopx(context,80f),DisplayUtil.dpTopx(context,220f),DisplayUtil.dpTopx(context,50f),mPaint)

        mPaint.color = Color.parseColor("#ff3300ff")
        mPaint.style = Paint.Style.FILL_AND_STROKE
        canvas?.drawCircle(DisplayUtil.dpTopx(context,220f),DisplayUtil.dpTopx(context,220f),DisplayUtil.dpTopx(context,50f),mPaint)

    }

}
