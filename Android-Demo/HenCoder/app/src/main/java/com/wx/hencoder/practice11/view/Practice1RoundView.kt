package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1RoundView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ff3300ff")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawRoundRect(
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,260f),
                    DisplayUtil.dpTopx(context,100f),
                    DisplayUtil.dpTopx(context,10f),
                    DisplayUtil.dpTopx(context,10f),
                    mPaint
            )
        }

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawRoundRect(
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,140f),
                    DisplayUtil.dpTopx(context,260f),
                    DisplayUtil.dpTopx(context,200f),
                    DisplayUtil.dpTopx(context,10f),
                    DisplayUtil.dpTopx(context,30f),
                    mPaint
            )
        }

    }

}
