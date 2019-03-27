package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1ArcView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    /*
    * drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom
    * 描述的是这个弧形所在的椭圆；startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
    * sweepAngle 是弧形划过的角度；
    * useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
    * */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ff9999ff")
        mPaint.style = Paint.Style.FILL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawArc(
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,80f),
                    DisplayUtil.dpTopx(context,260f),
                    DisplayUtil.dpTopx(context,220f),
                    -130f,
                    120f,
                    true,
                    mPaint
            )
        }

        mPaint.color = Color.parseColor("#ff9900ff")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawArc(
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,80f),
                    DisplayUtil.dpTopx(context,260f),
                    DisplayUtil.dpTopx(context,220f),
                    30f,
                    120f,
                    false,
                    mPaint
            )
        }

        mPaint.color = Color.parseColor("#ff3300ff")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawArc(
                    DisplayUtil.dpTopx(context,40f),
                    DisplayUtil.dpTopx(context,80f),
                    DisplayUtil.dpTopx(context,260f),
                    DisplayUtil.dpTopx(context,220f),
                    160f,
                    60f,
                    false,
                    mPaint
            )
        }

    }

}
