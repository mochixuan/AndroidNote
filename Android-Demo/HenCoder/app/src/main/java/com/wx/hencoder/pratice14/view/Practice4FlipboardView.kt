package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wx.hencoder.R

class Practice4FlipboardView : View {

    private var mPaint = Paint()

    private val cam = Camera()
    private val mMatrix = Matrix()
    private var degree = 0
    private var bitmap: Bitmap? = null
    private var bitmap1: Bitmap? = null
    private val point = PointF(240f,240f)
    private var bitmapWidth: Int = 0
    private var bitmapHeight: Int = 0
    private var centerX = 0f
    private var centerY = 0f

    @SuppressLint("ObjectAnimatorBinding")
    private var animator = ObjectAnimator.ofInt(this,"degree",0,360)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿

        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)
        bitmapWidth = bitmap!!.width
        bitmapHeight = bitmap!!.height
        bitmap1 = Bitmap.createBitmap(bitmap,0,bitmapHeight/2,bitmapWidth,bitmapHeight/2)

        centerX = point.x + bitmapWidth/2f
        centerY = point.y + bitmapHeight/2f

        animator.duration = 5000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator() as TimeInterpolator?

        cam.setLocation(0f,0f,-resources.displayMetrics.density*6)
    }

    fun setDegree(degree: Int) {
        this.degree = degree
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.clipRect(point.x,point.y,point.x+bitmapWidth,point.y+bitmapHeight/2)
        canvas?.drawBitmap(bitmap,point.x,point.y,mPaint)
        canvas?.restore()

        val newDegree = if (degree>180) (360 - degree) else degree

        cam.save()
        cam.rotateX(newDegree.toFloat())
        mMatrix.reset()
        cam.getMatrix(mMatrix)
        cam.restore()

        mMatrix.preTranslate(-centerX,-centerY)
        mMatrix.postTranslate(centerX,centerY)

        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap1,point.x,point.y+bitmapHeight/2,mPaint)
        canvas?.restore()
    }

}
