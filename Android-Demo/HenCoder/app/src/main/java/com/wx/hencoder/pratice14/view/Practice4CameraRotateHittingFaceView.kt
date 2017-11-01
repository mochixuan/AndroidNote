package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wx.hencoder.R

class Practice4CameraRotateHittingFaceView : View {

    private var mPaint = Paint()

    @SuppressLint("ObjectAnimatorBinding")
    var animator = ObjectAnimator.ofInt(this,"degree",0,360)
    private var degree = 0
    var bitmap:Bitmap? = null
    val startPoint = PointF(250f,100f)
    val mMatrix = Matrix()
    val camera = Camera()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿

        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.maps)
        val bitmapSample = Bitmap.createScaledBitmap(bitmap,bitmap!!.width*2,bitmap!!.height*2,true)
        bitmap?.recycle()
        bitmap = bitmapSample

        animator.duration = 5000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE

        /*
        * 高分辨率下提高清晰度 拉远镜头
        * */
        camera.setLocation(0f,0f,-resources.displayMetrics.density*6)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    //被调用了不知道动画怎么调用的
    fun setDegree(degree: Int) {
        this.degree = degree
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap?.width
        val bitmapHeight = bitmap?.height
        val centerX = startPoint.x+bitmapWidth!!/2
        val centerY = startPoint.y+bitmapHeight!!/2

        camera.save()
        mMatrix.reset()
        camera.rotateX(degree.toFloat())  //修改当前这片纸的投影
        camera.getMatrix(mMatrix)
        camera.restore()

        mMatrix.preTranslate(-centerX,-centerY)  //变换之前移动
        mMatrix.postTranslate(centerX,centerY)  //变换之前移动

        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap,startPoint.x,startPoint.y,mPaint)
        canvas?.restore()


    }

}
