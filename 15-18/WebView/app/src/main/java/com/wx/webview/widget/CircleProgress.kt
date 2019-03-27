package com.wx.webview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

/**
 * Created by wangxuan on 2018/2/5.
 */

class CircleProgress : View{

    private var mPaint: Paint? = null
    private var mProgress = 0
    private var ARC_WIDTH: Float = 0f
    private var mColorUnFinish: Int = 0
    private var mColorFinished: Int = 0

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        ARC_WIDTH = DisplayUtil.dpTopx(context!!,3f)
        mColorFinished = Color.parseColor("#FFA020F0")
        mColorUnFinish = Color.parseColor("#FFF0F0F0")
        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeWidth = ARC_WIDTH
        mPaint?.strokeCap = Paint.Cap.ROUND
        mPaint?.textAlign = Paint.Align.CENTER
        mPaint?.textSize = DisplayUtil.spTopx(context!!,16f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint?.strokeWidth = ARC_WIDTH
        val gap = ARC_WIDTH/2+0.5f
        val reactF = RectF(gap,gap,measuredWidth-gap,measuredHeight-gap)
        mPaint?.color = mColorUnFinish
        canvas.drawArc(reactF,0f,360f,false,mPaint!!)
        mPaint?.color = mColorFinished
        canvas.drawArc(reactF,-90f,mProgress*360f/100f,false,mPaint!!)
        val textHeight = -mPaint!!.ascent() - mPaint!!.descent()
        mPaint?.strokeWidth = 0f
        canvas.drawText(mProgress.toString(),measuredWidth/2f,(measuredHeight+textHeight)/2f,mPaint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val minSize = Math.min(width,height)
        setMeasuredDimension(minSize,minSize)
    }

    fun setProgress(progress: Int) {
        if (progress<0) {
            mProgress = 0
            return
        }
        if (progress>100) {
            mProgress = 100
            return
        }
        if (mProgress != progress) {
            mProgress = progress
            invalidate()
        }
    }

}