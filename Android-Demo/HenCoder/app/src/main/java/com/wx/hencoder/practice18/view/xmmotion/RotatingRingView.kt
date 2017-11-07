package com.wx.hencoder.practice18.view.xmmotion

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wx.hencoder.utils.DisplayUtil
import java.util.*

class RotatingRingView : View {

    private var mPaint = Paint()
    private var mRotatingAnimator: ObjectAnimator? = null
    private val mRingLines = arrayOfNulls<RingLineModel>(8)
    private val mParticles = arrayOfNulls<ParticleModel>(16)
    private var rotateAngle = 0f
    private var mParticleRefreshNum = 0

    init {
        val random = Random()
        for (i in 0..mRingLines.size-1) {
            val randomOffsetX = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,4f)
            val randomOffsetY = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,4f)
            val randomOffsetRadius = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,2f)
            mRingLines[i] = RingLineModel(randomOffsetX,randomOffsetY,randomOffsetRadius)
        }
    }

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffffffff")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        onStartAnim()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.rotate(rotateAngle,measuredWidth/2f,measuredHeight/2f)

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 1f
        mPaint.alpha = 255
        val ringX = measuredWidth/2f
        val ringY = measuredHeight/2f
        val ringR = Math.min(ringX,ringY)*0.6.toFloat()
        val sweepShader = SweepGradient(ringX,ringY,Color.parseColor("#00ffffff"),Color.parseColor("#ffffffff"))
        mPaint.shader = sweepShader
        for (i in 0..7) {
            canvas!!.drawCircle(ringX+mRingLines[i]!!.cX, ringY+mRingLines[i]!!.cY, ringR+mRingLines[i]!!.radius, mPaint)
        }

        mPaint.shader = null
        mPaint.style = Paint.Style.FILL
        val praticleR = DisplayUtil.dpTopx(context,4f)
        val praticleX = ringX+ringR
        val praticleY = ringY
        val random = Random()
        var spaceValue = 0f
        for (i in 0..mParticles.size-1) {

            val alpha = 255 - spaceValue.toInt()

            mPaint.alpha = if (alpha>=0 && alpha<=255) alpha else 0

            var randomOffsetX1 = 0f
            var randomOffsetY1 = 0f
            var randomOffsetX2 = 0f
            var randomOffsetY2 = 0f

            if (mParticleRefreshNum == 0) {
                randomOffsetX1 = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,7f)
                randomOffsetY1 = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,5f)
                randomOffsetX2 = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,6f)
                randomOffsetY2 = (random.nextFloat()-0.5f)*2*DisplayUtil.dpTopx(context,8f)
                mParticles[i] = ParticleModel(randomOffsetX1,randomOffsetY1,randomOffsetX2,randomOffsetY2)
            } else {
                randomOffsetX1 = mParticles[i]!!.cX1
                randomOffsetY1 = mParticles[i]!!.cY1
                randomOffsetX2 = mParticles[i]!!.cX2
                randomOffsetY2 = mParticles[i]!!.cY2
            }

            canvas!!.drawCircle(
                    praticleX-spaceValue*Math.cos(70*Math.PI/180).toFloat()+randomOffsetX1,
                    praticleY-spaceValue*Math.sin(70*Math.PI/180).toFloat()+randomOffsetY1,
                    praticleR,
                    mPaint
            )

            canvas!!.drawCircle(
                    praticleX-spaceValue*Math.cos(85*Math.PI/180).toFloat() + randomOffsetX2,
                    praticleY-spaceValue*Math.sin(85*Math.PI/180).toFloat() + randomOffsetY2,
                    praticleR,
                    mPaint
            )
            spaceValue = spaceValue + praticleR*1.6f
        }

        ++ mParticleRefreshNum
        if (mParticleRefreshNum>=5) {   //降低粒子刷新频率
            mParticleRefreshNum = 0
        }

        canvas?.restore()

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
            mRotatingAnimator!!.cancel()
        }
        mRotatingAnimator = ObjectAnimator.ofFloat(this,"rotateAngle",0f,360f)
        mRotatingAnimator!!.duration = 2000
        mRotatingAnimator!!.repeatCount = ObjectAnimator.INFINITE
        mRotatingAnimator!!.interpolator = LinearInterpolator()
        mRotatingAnimator!!.start()
    }

    fun setRotateAngle(rotateAngle:Float) {
        this.rotateAngle = rotateAngle
        invalidate()
    }

    fun onEndAnim() {
        if (mRotatingAnimator != null && mRotatingAnimator!!.isRunning) {
            mRotatingAnimator!!.cancel()
        }
    }

}
