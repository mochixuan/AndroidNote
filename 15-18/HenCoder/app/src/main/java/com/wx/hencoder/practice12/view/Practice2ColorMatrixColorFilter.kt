package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice2ColorMatrixColorFilter : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.batman)
        var matrix = floatArrayOf(
      1f, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 0.2f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
        )
        val colorMatrix = ColorMatrixColorFilter(
                 matrix
        )
        mPaint.colorFilter = colorMatrix
        canvas?.drawBitmap(bitmap,200f,200f,mPaint)

    }

}
