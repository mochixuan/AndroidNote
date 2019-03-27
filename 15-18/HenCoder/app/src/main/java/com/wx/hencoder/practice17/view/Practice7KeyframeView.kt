package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice7KeyframeView : View {

    private var mPaint = Paint()
    private val radius: Float? = DisplayUtil.dpTopx(context,80f)
    private val mRectF = RectF()
    private var progress = 0f

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿

        mPaint.textSize = DisplayUtil.dpTopx(context!!,40f)
        mPaint.textAlign = Paint.Align.CENTER
    }

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centerX = width/2f
        val centerY = height/2f

        mPaint.color = Color.parseColor("#E91E63")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,15f)
        mRectF.set(
                centerX-radius!!,
                centerY-radius!!,
                centerX+radius!!,
                centerY+radius!!
        )
        canvas?.drawArc(mRectF,135f,progress*2.7f,false,mPaint)

        mPaint.style = Paint.Style.FILL
        canvas?.drawText(
                progress.toInt().toString()+"%",
                centerX,
                centerY+(-mPaint.ascent()-mPaint.descent())/2,
                mPaint
        )

    }

}
