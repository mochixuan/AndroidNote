package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1HistogramView : View {

    private var mPaint = Paint()
    private val width: Int? = DisplayUtil.getWidth(context)
    private val height: Int? = DisplayUtil.getHeight(context)
    private val names = arrayOf("Froyo","GB","JB","Kitkat","L","M","O")
    private val ratios = arrayOf(1,10,12,17,20,30,10)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.WHITE
        mPaint.strokeWidth = 4f
        canvas?.drawLine(
                DisplayUtil.dpTopx(context,40f),
                DisplayUtil.dpTopx(context,260f),
                DisplayUtil.dpTopx(context,40f),
                DisplayUtil.dpTopx(context,40f),
                mPaint
        )

        canvas?.drawLine(
                DisplayUtil.dpTopx(context,40f),
                DisplayUtil.dpTopx(context,260f),
                width!!.toFloat()-DisplayUtil.dpTopx(context,40f),
                DisplayUtil.dpTopx(context,260f),
                mPaint
        )

        mPaint.textSize = DisplayUtil.dpTopx(context,12f)
        mPaint.textAlign = Paint.Align.CENTER
        names.forEachIndexed { index, item ->
            canvas?.drawText(
                    item,
                    (width!!.toFloat()-DisplayUtil.dpTopx(context,80f))/(names.size+1)*(index+1)+DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,280f),
                    mPaint
            )
        }

        mPaint.textSize = DisplayUtil.dpTopx(context,16f)
        canvas?.drawText(
                "直方图",
                width!!/2f,
                DisplayUtil.dpTopx(context,320f),
                mPaint
        )

        mPaint.strokeWidth = DisplayUtil.dpTopx(context,12f)
        mPaint.color = Color.parseColor("#ff00ffcc")
        ratios.forEachIndexed { index, item ->
            canvas?.drawLine(
                    (width!!.toFloat()-DisplayUtil.dpTopx(context,80f))/(names.size+1)*(index+1)+DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,258f),
                    (width!!.toFloat()-DisplayUtil.dpTopx(context,80f))/(names.size+1)*(index+1)+DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,258f)-DisplayUtil.dpTopx(context,item.toFloat()*6),
                    mPaint
            )
        }


    }

}
