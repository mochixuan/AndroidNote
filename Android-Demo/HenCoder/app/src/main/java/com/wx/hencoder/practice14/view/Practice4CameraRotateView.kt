package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R

class Practice4CameraRotateView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)

        canvas?.drawBitmap(bitmap,100f,100f,mPaint)

        val camera = Camera()


        canvas?.save()

        camera.save()
        camera.rotate(10f,10f,0f)
        camera.applyToCanvas(canvas)
        camera.restore()

        canvas?.drawBitmap(bitmap,450f,150f,mPaint)
        canvas?.restore()

    }

}
