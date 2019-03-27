package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice2XfermodeView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val saved = canvas?.saveLayer(null,null,Canvas.ALL_SAVE_FLAG)

        val option = BitmapFactory.Options()
        option.inSampleSize = 2
        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.batman,option)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.mipmap.batman_logo,option)
        val xferMode1 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas?.drawBitmap(bitmap1,20f,180f,mPaint)
        mPaint.xfermode = xferMode1
        canvas?.drawBitmap(bitmap2,20f,180f,mPaint)
        mPaint.xfermode = null

        val xferMode2 = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas?.drawBitmap(bitmap1,370f,180f,mPaint)
        mPaint.xfermode = xferMode2
        canvas?.drawBitmap(bitmap2,370f,180f,mPaint)
        mPaint.xfermode = null

        val xferMode3 = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas?.drawBitmap(bitmap1,720f,180f,mPaint)
        mPaint.xfermode = xferMode3
        canvas?.drawBitmap(bitmap2,720f,180f,mPaint)
        mPaint.xfermode = null

        canvas?.restoreToCount(saved!!)
    }

}
