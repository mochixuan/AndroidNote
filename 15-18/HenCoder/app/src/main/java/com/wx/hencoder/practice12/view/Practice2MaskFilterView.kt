package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice2MaskFilterView : View {

    private var mPaint = Paint()

    val width: Int? = DisplayUtil.getWidth(context)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    /*
    * 必须关闭硬件加速
    * */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.what_the_fuck,options)

        canvas?.drawBitmap(bitmap,width!!/2-150f,100f,mPaint)

        mPaint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.NORMAL) as MaskFilter?
        canvas?.drawBitmap(bitmap,width!!/2-150f,400f,mPaint)

        mPaint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.SOLID) as MaskFilter?
        canvas?.drawBitmap(bitmap,width!!/2-150f,700f,mPaint)

        mPaint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.INNER) as MaskFilter?
        canvas?.drawBitmap(bitmap,width!!/2-150f,1000f,mPaint)

        mPaint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.OUTER) as MaskFilter?
        canvas?.drawBitmap(bitmap,width!!/2-150f,1300f,mPaint)

    }

}
