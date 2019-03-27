package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.wx.hencoder.utils.DisplayUtil

class Practice5AfterOnDrawView : AppCompatImageView {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ffc107")
        mPaint.textSize = DisplayUtil.dpTopx(context,16f)

        val bounds = drawable.bounds
        canvas?.save()
        canvas?.concat(imageMatrix)  //真实图片矩阵中
        canvas?.drawText(
                "尺寸:"+bounds.width()+" x "+bounds.height(),
                40f,
                40f+mPaint.fontSpacing,
                mPaint
        )
        canvas?.restore()

    }

}
