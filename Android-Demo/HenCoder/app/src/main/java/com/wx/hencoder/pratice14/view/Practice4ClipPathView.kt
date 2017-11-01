package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice4ClipPathView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)

        val path = Path()
        path.addCircle(300f,300f,150f,Path.Direction.CW)

        canvas?.save()
        canvas?.clipPath(path)
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)
        canvas?.restore()

        val path1 = Path()
        path1.moveTo(300f,300f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path1.addArc(150f,150f,450f,450f,90f,270f)
        }
        path1.lineTo(400f,150f)
        path1.lineTo(400f,100f)
        path1.lineTo(100f,100f)
        path1.lineTo(100f,400f)
        path1.lineTo(300f,400f)
        canvas?.translate(350f,0f)
        canvas?.save()
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)
        canvas?.restore()


    }

}
