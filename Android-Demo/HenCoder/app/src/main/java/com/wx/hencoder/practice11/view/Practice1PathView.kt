package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.widget.ScrollView

class Practice1PathView : ScrollView {

    private var mPaint = Paint()
    private var mPath = Path()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ffff0000")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPath.addArc(200f, 200f, 400f, 400f, -225f, 225f)
            mPath.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false);
            mPath.lineTo(400f, 542f);
        };
        canvas?.drawPath(mPath,mPaint)
        mPath.reset()

        mPaint.color = Color.parseColor("#ff9999ff")
        mPath.addCircle(600f,600f,100f,Path.Direction.CW)
        mPath.addCircle(750f,600f,100f,Path.Direction.CCW)
        //Path.setFillType(fillType) 是用来设置图形自相交时的填充算法的
        //https://juejin.im/post/5962a3746fb9a06ba2687226
        mPath.fillType = Path.FillType.EVEN_ODD
        canvas?.drawPath(mPath,mPaint)
        mPath.reset()

        mPaint.color = Color.parseColor("#ff9900ff")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 6f
        mPath.moveTo(400f,800f)
        mPath.lineTo(500f,900f)
        mPath.rLineTo(200f,0f)  //r的参数是距离不是点
        mPath.close()                   //连接起点和终点
        canvas?.drawPath(mPath,mPaint)
        mPath.reset()

        //二次贝塞尔曲线的起点就是当前位置，而参数中的 x1, y1 和 x2, y2 则分别是控制点和终点的坐标。
        //第一个点是控制点
        // 和 rLineTo(x, y) 同理，rQuadTo(dx1, dy1, dx2, dy2) 的参数也是相对坐标

        mPaint.color = Color.parseColor("#ff3300ff")
        mPath.moveTo(400f,1000f)
        mPath.quadTo(600f,950f,800f,1000f)
        canvas?.drawPath(mPath,mPaint)
        mPath.reset()

        mPath.moveTo(200f,1200f)
        mPath.cubicTo(400f,1050f,600f,1350f,800f,1200f)
        canvas?.drawPath(mPath,mPaint)
        mPath.reset()

    }

}
