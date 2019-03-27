package com.wx.hencoder.practice18.view.xmmotion

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wx.hencoder.utils.DisplayUtil



class ConnRingView : View {

    private var mPaint = Paint()
    private var mRotatingAnimator: ObjectAnimator? = null
    private var rotateAngle = 0f

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffffffff")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.rotate(rotateAngle,measuredWidth/2f,measuredHeight/2f)

        drawConnectView(canvas!!)

        canvas.restore()
    }

    private fun drawConnectView(canvas: Canvas) {

        val ringX = measuredWidth/2f
        val ringY = measuredHeight/2f
        val ringR0 = Math.min(ringX,ringY)*0.6.toFloat()
        val ringR1 = Math.min(ringX,ringY)*0.7.toFloat()
        val ringR2 = ringR1 + DisplayUtil.dpTopx(context,10f)
        val ringR3 = ringR1 + DisplayUtil.dpTopx(context,13f)
        val ringR4 = ringR1 + DisplayUtil.dpTopx(context,16f)

        //大实圈
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,16f)
        mPaint.alpha = 100
        canvas.drawCircle(ringX, ringY, ringR1, mPaint)

        //动画圈
        mPaint.style = Paint.Style.STROKE
        val shade1 = LinearGradient(
                ringX+ringR1,
                ringY-ringR1,
                ringX+ringR1,
                ringY+ringR1,
                intArrayOf(
                        Color.parseColor("#00ffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#00ffffff")
                ),
                null,
                Shader.TileMode.CLAMP
        )
        mPaint.shader = shade1
        mPaint.alpha = 255
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,16f)
        val rectRing1 = RectF(ringX-ringR1,ringY-ringR1,ringX+ringR1,ringY+ringR1)
        canvas.drawArc(rectRing1,-90f,180f,false,mPaint)

        val shade2 = LinearGradient(
                ringX+ringR1,
                ringY-ringR1*0.8f,
                ringX+ringR1,
                ringY+ringR1*0.8f,
                intArrayOf(
                        Color.parseColor("#00ffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#ffffffff"),
                        Color.parseColor("#00ffffff")
                ),
                null,
                Shader.TileMode.CLAMP
        )
        mPaint.shader = shade2

        val offsetY = DisplayUtil.dpTopx(context,6f)

        mPaint.strokeWidth = DisplayUtil.dpTopx(context,4f)
        mPaint.alpha = 100
        val rectRing2 = RectF(ringX-ringR2,ringY-ringR2+offsetY,ringX+ringR2,ringY+ringR2-offsetY)
        canvas.drawArc(rectRing2,-50f,100f,false,mPaint)

        mPaint.strokeWidth = DisplayUtil.dpTopx(context,5f)
        mPaint.alpha = 70
        val rectRing3 = RectF(ringX-ringR3,ringY-ringR3+offsetY,ringX+ringR3,ringY+ringR3-offsetY)
        canvas.drawArc(rectRing3,-45f,90f,false,mPaint)

        mPaint.strokeWidth = DisplayUtil.dpTopx(context,6f)
        mPaint.alpha = 40
        val rectRing4 = RectF(ringX-ringR4,ringY-ringR4+offsetY,ringX+ringR4,ringY+ringR4-offsetY)
        canvas.drawArc(rectRing4,-40f,80f,false,mPaint)

        mPaint.shader = null

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onEndAnim()
    }

    /*
    * 发现默认使用的是加速插补器，所以到达终点出现卡顿/停顿，就是它导致的，更改为匀速的插补器即可
    * */
    fun onStartAnim() {
        if (mRotatingAnimator != null && mRotatingAnimator!!.isRunning) {
            return
        }
        mRotatingAnimator = ObjectAnimator.ofFloat(this,"rotateAngle",0f,360f)
        mRotatingAnimator!!.duration = 10000
        mRotatingAnimator!!.repeatCount = ObjectAnimator.INFINITE
        mRotatingAnimator!!.interpolator = LinearInterpolator()
        mRotatingAnimator!!.start()
    }
    fun onEndAnim() {
        if (mRotatingAnimator != null && mRotatingAnimator!!.isRunning) {
            mRotatingAnimator!!.cancel()
        }
    }

    private fun setRotateAngle(rotateAngle:Float) {
        this.rotateAngle = rotateAngle
        invalidate()
    }




}
