package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice2LightingColorFilterView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val option = BitmapFactory.Options()
        option.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.batman,option)
        val lightingColorFilter = LightingColorFilter(0x00ffff,0x000000)
        mPaint.colorFilter = lightingColorFilter
        canvas?.drawBitmap(
                    bitmap,
                    400f,
                    200f,
                    mPaint
        )

        val lightingColorFilter1 = LightingColorFilter(0xffffff,0xe00000)
        mPaint.colorFilter = lightingColorFilter1
        canvas?.drawBitmap(
                bitmap,
                400f,
                600f,
                mPaint
        )

    }

}
