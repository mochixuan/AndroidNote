package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice4TranslateView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)

        canvas?.save()
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.maps,options)
        canvas?.translate(300f,300f)
        canvas?.drawBitmap(bitmap1,100f,100f,mPaint)
        canvas?.restore()   //保持其他的不变

    }

}
