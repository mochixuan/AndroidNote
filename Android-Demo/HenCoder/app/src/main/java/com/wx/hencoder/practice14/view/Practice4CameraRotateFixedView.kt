package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice4CameraRotateFixedView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)
        mPaint.alpha = 80
        canvas?.drawBitmap(bitmap,100f,100f,mPaint)
        mPaint.alpha = 255

        val cam = Camera()

        canvas?.save()

        val centX = DisplayUtil.dpTopx(context,150f)-200
        val centY = DisplayUtil.dpTopx(context,150f)-200

        cam.save()
        cam.rotateX(30f)
        cam.setLocation(0f,0f,-10f)  //在图片过大时有效，这里可以不用
        canvas?.translate(centX,centY)
        cam.applyToCanvas(canvas)
        canvas?.translate(-centX,-centY)
        cam.restore()

        canvas?.drawBitmap(bitmap,100f,100f,mPaint)

        canvas?.restore()

    }

}
