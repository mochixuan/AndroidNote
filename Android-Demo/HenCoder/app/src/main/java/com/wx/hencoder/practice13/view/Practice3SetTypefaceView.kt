package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3SetTypefaceView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val text = "Hello MoChiXuan"
        mPaint.textSize = DisplayUtil.dpTopx(context,20f)

        mPaint.typeface = Typeface.DEFAULT
        canvas?.drawText(text,100f,140f,mPaint)

        mPaint.typeface = Typeface.SERIF
        canvas?.drawText(text,100f,240f,mPaint)

        mPaint.typeface = Typeface.createFromAsset(context.assets,"Satisfy-Regular.ttf")
        canvas?.drawText(text,100f,340f,mPaint)

    }

}
