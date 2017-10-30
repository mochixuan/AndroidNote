package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice2ShadowLayerView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }



    /*
    * 在硬件加速开启的情况下， setShadowLayer() 只支持文字的绘制，文字之外的绘制必须关闭硬件加速才能正常绘制阴影
    * 如果 shadowColor 是半透明的，阴影的透明度就使用 shadowColor 自己的透明度；而如果 shadowColor 是不透明的，阴影的透明度就使用 paint 的透明度。
    * */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.textSize = DisplayUtil.dpTopx(context,24f)
        mPaint.setShadowLayer(10f,0f,0f,Color.RED)
        mPaint.textAlign = Paint.Align.CENTER
        canvas?.drawText(
                "Hello HenCoder",
                DisplayUtil.dpTopx(context,150f),
                DisplayUtil.dpTopx(context,140f),
                mPaint
        )

        //如果要清除阴影层，使用 clearShadowLayer()

    }

}
