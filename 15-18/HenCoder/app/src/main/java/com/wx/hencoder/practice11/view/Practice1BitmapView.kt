package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice1BitmapView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(
                BitmapFactory.decodeResource(context.resources,R.mipmap.mochixuan),
                0f,
                0f,
                mPaint
        )

        mPaint.textSize = 36f
        mPaint.color = Color.RED
        canvas?.drawText("MoChiXuan",100f,100f,mPaint)

    }

}
