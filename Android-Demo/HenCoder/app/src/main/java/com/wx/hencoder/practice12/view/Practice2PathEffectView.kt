package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Practice2PathEffectView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.strokeWidth = 2f
        mPaint.style = Paint.Style.STROKE

        val path = Path()

        for (i in 1..7) {

            var pathEffect = PathEffect()

            if (i == 2) {
                pathEffect = CornerPathEffect(50f);
            } else if (i == 3) {

            } else if (i == 4) {

            } else if (i == 5) {

            } else if (i == 6) {

            } else if (i == 7) {

            }

            mPaint.pathEffect = pathEffect

            path.moveTo(100f,100f+i*200)
            path.lineTo(200f,200f+i*200)
            path.lineTo(320f,90f+i*200)
            path.lineTo(400f,0f+i*200)
            path.lineTo(530f,110f+i*200)
            path.lineTo(610f,190f+i*200)
            path.lineTo(750f,80f+i*200)
            canvas?.drawPath(path,mPaint)
            path.reset()

        }



    }

}
