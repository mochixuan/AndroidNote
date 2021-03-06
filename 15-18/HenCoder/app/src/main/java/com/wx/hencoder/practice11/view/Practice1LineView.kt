package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1LineView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.strokeWidth = 10f
        mPaint.color = Color.parseColor("#ff99ffff")
        canvas?.drawLine(
                DisplayUtil.dpTopx(context,50f),
                DisplayUtil.dpTopx(context,50f),
                DisplayUtil.dpTopx(context,250f),
                DisplayUtil.dpTopx(context,250f),
                mPaint
        )

    }

}
