package com.wx.hencoder.practice18.view.likes

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil


class LikesTextView2 : View,View.OnClickListener {

    private var mPaint = Paint()
    private var isLikes: Boolean = false
    private var upNums:Int = 0
    private var centerNums:Int = 9998
    private var downNums: Int = 0
    private var scroll: Float = 0f

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnClickListener(this)

        mPaint.textAlign = Paint.Align.RIGHT
        mPaint.color = Color.parseColor("#ffcccccc")
        mPaint.textSize = DisplayUtil.dpTopx(context,16f)

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()

        upNums = centerNums - 1
        downNums = centerNums + 1

        val textHeight = -mPaint.descent()-mPaint.ascent()

        canvas?.clipRect(0f, height/2f -textHeight, width.toFloat(), height/2f + textHeight)

        val rightX = width/2f + mPaint.measureText(centerNums.toString())/2

        val upY = height/2f + textHeight*0.5f - mPaint.fontSpacing + scroll*mPaint.fontSpacing
        val centerY = height/2f + textHeight*0.5f + scroll*mPaint.fontSpacing
        val downY = height/2f + textHeight*0.5f + mPaint.fontSpacing+ scroll*mPaint.fontSpacing

        canvas?.drawText(upNums.toString(), rightX, upY, mPaint)
        canvas?.drawText(centerNums.toString(), rightX, centerY, mPaint)
        canvas?.drawText(downNums.toString(), rightX, downY, mPaint)

        canvas?.restore()

    }

    private fun onAnimator(isAdd:Boolean) {
        var endValue: Float = 1f
        if (isAdd) {
            endValue = -1f
        }
        val animator = ObjectAnimator.ofFloat(this,"scroll",0f,endValue)
        animator.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (isAdd) {
                    ++centerNums
                } else {
                    --centerNums
                }
                scroll = 0f
            }
        })
        animator.duration = 300
        animator.start()
    }

    fun setScroll(scroll:Float) {
        this.scroll = scroll
        invalidate()
    }
    fun getScroll(): Float {
        return scroll
    }

    override fun onClick(p0: View?) {
        if (isLikes) {
            onAnimator(false)
        } else {
            onAnimator(true)
        }
        isLikes = !isLikes
    }

}
