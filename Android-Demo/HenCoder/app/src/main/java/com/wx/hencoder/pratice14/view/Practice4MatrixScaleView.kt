package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice4MatrixScaleView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)
        mPaint.alpha = 80
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)
        mPaint.alpha = 255

        val matrix = Matrix()
        matrix.reset()
        matrix.postScale(1.3f,2f)

        canvas?.save()
        canvas?.concat(matrix)
        canvas?.drawBitmap(bitmap,300f,100f,mPaint)
        canvas?.restore()

    }

}
