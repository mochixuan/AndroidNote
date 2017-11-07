package com.wx.hencoder.practice18.view.likes

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil


class LikesImageView : View {

    private var mPaint = Paint()
    private var isLikes: Boolean = false

    private var selectBitmap: Bitmap? = null
    private var selectShiningBitmap: Bitmap? = null
    private var unselectBitmap: Bitmap? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun setIsLikes(isLikes: Boolean) {
        this.isLikes = isLikes
    }

    private fun init() {
        unselectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_comment_like)
        selectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected)
        selectShiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected_shining)

        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.parseColor("#E45B41")
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,2f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (isLikes) {
            onDrawSelectBitmap(canvas!!)
            onDrawBitmapWave(canvas!!)
        } else {
            onDrawUnSelectBitmap(canvas!!)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        var realWidth = 0

        when (specWidthMode) {
            //相当于wrap_content (等于或小于这个能够给予的最大值)
            MeasureSpec.AT_MOST -> {
                realWidth = selectBitmap!!.width
            }
            //为math_parent或者固定值时
            MeasureSpec.EXACTLY -> {
                realWidth = specWidthSize
            }
            //没有确定值或为0这个和EXACTLY一样处理一般，想要多大就多大
            MeasureSpec.UNSPECIFIED -> {
                realWidth = selectBitmap!!.width
            }
        }

        val specHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val specHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        var realHeight = 0

        when (specHeightMode) {
            //相当于wrap_content (等于或小于这个能够给予的最大值)
            MeasureSpec.AT_MOST -> {
                realHeight = selectBitmap!!.height
            }
            //为math_parent或者固定值时
            MeasureSpec.EXACTLY -> {
                realHeight = specHeightSize
            }
            //没有确定值或为0这个和EXACTLY一样处理一般，想要多大就多大
            MeasureSpec.UNSPECIFIED -> {
                realHeight = selectBitmap!!.height
            }
        }

        setMeasuredDimension(realWidth,realHeight)

    }

    private fun onDrawUnSelectBitmap(canvas: Canvas) {
        canvas?.drawBitmap(
                unselectBitmap,
                width/2f - unselectBitmap!!.width/2f,
                height/2f-unselectBitmap!!.height/2f,
                mPaint
        )
    }



    private fun onDrawSelectBitmap(canvas: Canvas) {
        canvas?.drawBitmap(
                selectShiningBitmap,
                width/2f+3f- unselectBitmap!!.width/2f,
                height/2f-selectBitmap!!.height+3,
                mPaint
        )
        canvas?.drawBitmap(
                selectBitmap,
                width/2f- unselectBitmap!!.width/2f,
                height/2f-selectBitmap!!.height/2f,
                mPaint
        )
    }

    private fun onDrawBitmapWave(canvas: Canvas) {
        if (circle>0.999) return
        val radius: Float = (((1-circle)*0.6+1)*selectBitmap!!.width/2).toFloat()
        mPaint.alpha = (circle*255).toInt()
        val cx = width/2f
        val cy = height/2f
        canvas.drawCircle(
                cx,
                cy,
                radius,
                mPaint
        )
        mPaint.alpha = 255
    }

    //先缩小再放大动画
   fun onAnimator() {
        val scaleX1Anim = ObjectAnimator.ofFloat(this,"scaleX",1f,0.8f)
        val scaleY1Anim = ObjectAnimator.ofFloat(this,"scaleY",1f,0.8f)

        val scaleX2Anim = ObjectAnimator.ofFloat(this,"scaleX",0.8f,1.0f)
        val scaleY2Anim = ObjectAnimator.ofFloat(this,"scaleY",0.8f,1.0f)
        scaleX2Anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                isLikes = !isLikes
                invalidate()
            }
        })

        val animatorSet = AnimatorSet()
        animatorSet.play(scaleX1Anim).with(scaleY1Anim).before(scaleX2Anim)

        if(isLikes) {
            animatorSet.play(scaleX2Anim).with(scaleY2Anim)
        } else {
            val circle = ObjectAnimator.ofFloat(this,"circle",1f,0f)
            animatorSet.play(scaleX2Anim).with(scaleY2Anim).with(circle)
        }
        animatorSet.duration = 100
        animatorSet.start()

    }

    private var circle = 1f
    private fun setCircle(circle:Float) {
        this.circle = circle
        invalidate()
    }
    private fun getCircle():Float {
        return circle
    }


}
