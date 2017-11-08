package com.wx.hencoder.practice18.view.xmmotion

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.wx.hencoder.utils.DisplayUtil


class SolidRingView : View {

    private var mPaint = Paint()
    private var mRingProgress = 0f
    private var mAnimator: ValueAnimator = ValueAnimator()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffffffff")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val ringX = measuredWidth/2f
        val ringY = measuredHeight/2f
        val ringR0 = Math.min(ringX,ringY)*0.6.toFloat()
        val ringR1 = Math.min(ringX,ringY)*0.7.toFloat()

        //最内圆环虚线
        mPaint.style = Paint.Style.STROKE
        mPaint.alpha = 150
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,3f)
        val pathEffect = DashPathEffect(floatArrayOf(DisplayUtil.dpTopx(context,1f), DisplayUtil.dpTopx(context,2f)), 0f)
        mPaint.pathEffect = pathEffect
        canvas!!.drawCircle(ringX,ringY,ringR0,mPaint)
        mPaint.pathEffect = null
        //最内圆环实线进度
        mPaint.alpha = 255
        val rectRing0 = RectF(ringX-ringR0,ringY-ringR0,ringX+ringR0,ringY+ringR0)
        canvas.drawArc(rectRing0,-90f,mRingProgress,false,mPaint)
        //最内圆环实线进度圆点
        mPaint.style = Paint.Style.FILL
        canvas.drawCircle(
                ringX+Math.sin(mRingProgress*Math.PI/180).toFloat()*ringR0,
                ringY-Math.cos(mRingProgress*Math.PI/180).toFloat()*ringR0,
                DisplayUtil.dpTopx(context,4f),
                mPaint
        )

    }


    fun setRingProgress(ringProgress:Int,isWantAnin:Boolean) {

        if (ringProgress < 0 && ringProgress > 100) return

        val newProgress = ringProgress*360.toFloat()/100

        if (mAnimator.isRunning) mAnimator.cancel()

        if (newProgress == mRingProgress) return  //节约点消耗

        if (isWantAnin) {
            mAnimator.setFloatValues(mRingProgress,newProgress)
            mAnimator.duration = 10*Math.abs((mRingProgress-newProgress).toDouble()).toLong()
            mAnimator.interpolator = AccelerateInterpolator()
            mAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener{
                override fun onAnimationUpdate(valueAnimator: ValueAnimator?) {
                    val value:Float = valueAnimator!!.getAnimatedValue() as Float
                    if (value != mRingProgress) {
                        mRingProgress = value
                        invalidate()
                    }
                }
            })
            mAnimator.start()
        } else {
            this.mRingProgress = newProgress
            invalidate()
        }
    }
    fun getRingProgress():Int {
        return (mRingProgress*100/360).toInt()
    }

}
