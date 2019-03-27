package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice2StrokeJoinView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        mPaint.strokeWidth = DisplayUtil.dpTopx(context,16f)
        mPaint.style = Paint.Style.STROKE

        var path = Path()

        path.moveTo(
                DisplayUtil.dpTopx(context,10f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,80f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,40f),
                DisplayUtil.dpTopx(context,80f)
        )
        mPaint.strokeJoin = Paint.Join.MITER
        canvas?.drawPath(path,mPaint)
        path.reset()


        path.moveTo(
                DisplayUtil.dpTopx(context,120f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,190f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,150f),
                DisplayUtil.dpTopx(context,80f)
        )
        mPaint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawPath(path,mPaint)
        path.reset()


        path.moveTo(
                DisplayUtil.dpTopx(context,210f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,280f),
                DisplayUtil.dpTopx(context,40f)
        )
        path.lineTo(
                DisplayUtil.dpTopx(context,250f),
                DisplayUtil.dpTopx(context,80f)
        )
        mPaint.strokeJoin = Paint.Join.ROUND
        canvas?.drawPath(path,mPaint)
        path.reset()

    }

}
