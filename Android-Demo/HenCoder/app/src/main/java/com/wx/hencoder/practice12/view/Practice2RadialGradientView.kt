package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice2RadialGradientView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val shader = RadialGradient(
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,80f).toFloat(),
                Color.BLUE,
                Color.RED,
                Shader.TileMode.CLAMP
        )
        mPaint.shader = shader
        canvas?.drawCircle(
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,80f).toFloat(),
                mPaint
        )

    }

}
