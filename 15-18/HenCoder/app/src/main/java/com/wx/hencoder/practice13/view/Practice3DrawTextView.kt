package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3DrawTextView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.textSize = DisplayUtil.dpTopx(context,18f)
        canvas?.drawText("Hello HenCoder",300f,100f,mPaint)


        mPaint.textSize = DisplayUtil.dpTopx(context,12f)
        mPaint.style = Paint.Style.STROKE
        val path = Path()
        path.moveTo(100f,400f)
        path.lineTo(300f,500f)
        path.lineTo(500f,300f)
        path.lineTo(700f,400f)
        path.lineTo(900f,500f)
        mPaint.pathEffect = CornerPathEffect(50f)
        mPaint.color = Color.RED
        canvas?.drawPath(path,mPaint)
        mPaint.color = Color.BLACK
        canvas?.drawTextOnPath("Hello MoChiXuan Your Heart Will Go On",path,0f,0f,mPaint)

    }

}
