package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice7OfObjectView : View {

    private var mPaint = Paint()
    private val radio: Float? = DisplayUtil.dpTopx(context,20f)
    private val position = PointF()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿

        mPaint.color = Color.parseColor("#009688")

    }

    fun getPosition(): PointF {
        return position
    }

    fun setPosition(position: PointF) {
        this.position.set(position)
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val newWidth = width - radio!!*4
        val newHeight = height - radio*6

        canvas?.drawCircle(
                radio*2+width*position.x,
                radio*2+height*position.y,
                radio,
                mPaint
        )

    }

}
