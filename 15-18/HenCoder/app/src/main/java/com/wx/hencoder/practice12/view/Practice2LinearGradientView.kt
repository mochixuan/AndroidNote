package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice2LinearGradientView : View {

    private var mPaint = Paint()

    private val width: Int? = DisplayUtil.getWidth(context)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /*
        * x0,y0,x0,y1:分别代表是坐标位置而不是要画的图形的坐标
        * */

        val shader1 = LinearGradient(
            width!!/2-200f,
            200f,
            width!!/2+200f,
            600f,
            Color.parseColor("#FF0000"),
            Color.parseColor("#00CED1"),
            Shader.TileMode.CLAMP
        )
        mPaint.shader = shader1
        canvas?.drawCircle(width!!/2f,400f,200f,mPaint)

        val shader2 = LinearGradient(
                width!!/2-100f,
                600f,
                width!!/2+100f,
                800f,
                Color.parseColor("#FF0000"),
                Color.parseColor("#00CED1"),
                Shader.TileMode.MIRROR
        )
        mPaint.shader = shader2
        canvas?.drawCircle(width!!/2f,800f,200f,mPaint)

        val shader3 = LinearGradient(
                width!!/2-100f,
                1000f,
                width!!/2+100f,
                1200f,
                Color.parseColor("#FF0000"),
                Color.parseColor("#00CED1"),
                Shader.TileMode.REPEAT
        )
        mPaint.shader = shader3
        canvas?.drawCircle(width!!/2f,1200f,200f,mPaint)

    }

}
