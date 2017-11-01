package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice4ClipRectView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)

        mPaint.alpha = 40
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)

        mPaint.alpha = 255
        canvas?.save()
        canvas?.clipRect(100f,100f,400f,200f)
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)
        canvas?.restore()

        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 4f
        canvas?.drawRect(100f,100f,400f,200f,mPaint)

    }

}
