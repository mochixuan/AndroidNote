package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout

class Practice5DispatchDrawLayout : LinearLayout {

    private var mPaint = Paint()
    private val mPattern = Pattern()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        mPattern.draw(canvas!!)
    }

    inner class Pattern {

        private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        private var mSpots: ArrayList<Spot> ? = null

        init {
            mPaint.color = Color.parseColor("#A0E91E63")
            mSpots = ArrayList<Spot>()
            mSpots?.add(Spot(0.24f, 0.2f, 0.026f))
            mSpots?.add(Spot(0.60f, 0.3f, 0.067f))
            mSpots?.add(Spot(0.38f, 0.50f, 0.067f))
            mSpots?.add(Spot(0.5f, 0.68f, 0.083f))
        }

        fun draw(canvas: Canvas) {
            mSpots?.forEachIndexed { index, spot ->
                canvas.drawCircle(
                        spot.relativeX*width,
                        spot.relativeY*height,
                        spot.relativeSize*height,
                        mPaint
                )
            }
        }

        inner class Spot(
                var relativeX: Float,
                var relativeY: Float,
                var relativeSize: Float
        )

    }

}
