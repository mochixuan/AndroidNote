package com.wx.hencoder.practice18.view.likes

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class LikesTextView : View{

    private var mPaint = Paint()
    private var isLikes: Boolean = false
    private var upNums:Int = 0
    private var centerNums:Int = 0
    private var initNums:Int = 0
    private var downNums: Int = 0
    private var scroll: Float = 0f

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.textAlign = Paint.Align.RIGHT
        mPaint.isAntiAlias = true
    }

    fun setTextColor(color: Int) {
        mPaint.color = color
    }
    fun setTextSize(textSize: Float) {
        mPaint.textSize = textSize
    }
    fun setCenterNums(centerNums:Int) {
        this.centerNums = centerNums
        initNums = centerNums
    }
    fun setIsLikes(isLikes: Boolean) {
        this.isLikes = isLikes
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        var realWidth = 0

        when (specMode) {
            //相当于wrap_content (等于或小于这个能够给予的最大值)
            MeasureSpec.AT_MOST -> {
                realWidth = Math.ceil(mPaint.measureText((centerNums+1).toString()).toDouble()).toInt()
            }
            //为math_parent或者固定值时
            MeasureSpec.EXACTLY -> {
                realWidth = specSize
            }
            //没有确定值或为0这个和EXACTLY一样处理一般，想要多大就多大
            MeasureSpec.UNSPECIFIED -> {
                realWidth = Math.ceil(mPaint.measureText((centerNums+1).toString()).toDouble()).toInt()
            }
        }

        val specHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val specHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        var realHeight = 0

        when (specHeightMode) {
            //相当于wrap_content (等于或小于这个能够给予的最大值)
            MeasureSpec.AT_MOST -> {
                realHeight = (-mPaint.ascent()-mPaint.descent()).toInt()
            }
            //为math_parent或者固定值时
            MeasureSpec.EXACTLY -> {
                realHeight = specHeightSize
            }
            //没有确定值或为0这个和EXACTLY一样处理一般，想要多大就多大
            MeasureSpec.UNSPECIFIED -> {
                realHeight = (-mPaint.ascent()-mPaint.descent()).toInt()
            }
        }

        setMeasuredDimension(realWidth,realHeight)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()

        upNums = getUpNums()
        downNums = getDownNums()

        val textHeight = -mPaint.descent()-mPaint.ascent()

        canvas?.clipRect(0f, height/2f -textHeight, width.toFloat(), height/2f + textHeight)

        val rightX = width/2f + mPaint.measureText(initNums.toString())/2

        val upY = height/2f + textHeight*0.5f - mPaint.fontSpacing + scroll*mPaint.fontSpacing
        val centerpPrefixY = height/2f + textHeight*0.5f
        val centerSuffixY = height/2f + textHeight*0.5f + scroll*mPaint.fontSpacing
        val downY = height/2f + textHeight*0.5f + mPaint.fontSpacing+ scroll*mPaint.fontSpacing

        val centerNumsSuffix  = getCenterNumsSuffix(!isLikes)

        canvas?.drawText(upNums.toString(), rightX, upY, mPaint)
        canvas?.drawText(getCenterNumsPrefix(!isLikes), rightX-mPaint.measureText(centerNumsSuffix), centerpPrefixY, mPaint)
        canvas?.drawText(centerNumsSuffix, rightX, centerSuffixY, mPaint)
        canvas?.drawText(downNums.toString(), rightX, downY, mPaint)

        canvas?.restore()

    }

    fun getUpNums(): Int {
        val upValue = centerNums - 1
        if (upValue.toString().length != centerNums.toString().length) {
            return upValue
        } else {
            val endValue = Math.pow(10.toDouble(),getChangeDiget(true).toDouble()).toInt()
            return upValue - (upValue/endValue).toInt()*endValue
        }
    }

    fun getCenterNumsPrefix(isAdd: Boolean): String {
        if (isAdd) {
            val downValue = centerNums + 1
            if (downValue.toString().length != centerNums.toString().length) {
                return ""
            } else {
                val endValue = Math.pow(10.toDouble(),getChangeDiget(false).toDouble()).toInt()
                return (downValue/endValue).toString()
            }
        } else {
            val upValue = centerNums - 1
            if (upValue.toString().length != centerNums.toString().length) {
                return ""
            } else {
                val endValue = Math.pow(10.toDouble(),getChangeDiget(true).toDouble()).toInt()
                return (upValue/endValue).toString()
            }
        }
    }

    fun getCenterNumsSuffix(isAdd: Boolean): String {
        if (isAdd) {
            val downValue = centerNums + 1
            if (downValue.toString().length != centerNums.toString().length) {
                return centerNums.toString()
            } else {
                val strLength = centerNums.toString()
                val diget = getChangeDiget(false)
                return strLength.substring(strLength.length-diget,strLength.length)
            }
        } else {
            val upValue = centerNums - 1
            if (upValue.toString().length != centerNums.toString().length) {
                return centerNums.toString()
            } else {
                val strLength = centerNums.toString()
                val diget = getChangeDiget(true)
                return strLength.substring(strLength.length-diget,strLength.length)
            }
        }
    }

    fun getDownNums(): Int {
        val downValue = centerNums + 1
        if (downValue.toString().length != centerNums.toString().length) {
            return downValue
        } else {
            val endValue = Math.pow(10.toDouble(),getChangeDiget(false).toDouble()).toInt()
            return downValue - (downValue/endValue).toInt()*endValue
        }
    }

    fun getChangeDiget(isUp: Boolean): Int {
        var changeDiget = 0
        if (isUp) {
            val upValue = centerNums - 1
            for (i in 0..centerNums.toString().length) {
                val auxValue = Math.pow(10.toDouble(),i.toDouble()).toInt()
                if (upValue/auxValue == centerNums/auxValue) {
                    changeDiget = i
                    break
                }

            }
        } else {
            val downValue = centerNums + 1
            for (i in 0..downValue.toString().length) {
                val auxValue = Math.pow(10.toDouble(),i.toDouble()).toInt()
                if (downValue/auxValue == centerNums/auxValue) {
                    changeDiget = i
                    break
                }
            }
        }
        return changeDiget
    }

    private var animator:ObjectAnimator? = null
    fun onAnimator() {
        if (animator != null && animator!!.isRunning) {
            animator!!.cancel()
        }
        var endValue: Float = -1f
        if (isLikes) {
            endValue = 1f
        }
        animator = ObjectAnimator.ofFloat(this,"scroll",0f,endValue)
        animator!!.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (!isLikes) {
                    ++centerNums
                } else {
                    --centerNums
                }
                isLikes = !isLikes
                scroll = 0f
            }
        })
        animator!!.duration = 200
        animator!!.start()
    }

    fun setScroll(scroll:Float) {
        this.scroll = scroll
        invalidate()
    }
    fun getScroll(): Float {
        return scroll
    }

}
