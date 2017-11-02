package com.wx.hencoder.practice11.view

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7ArgbEvaluatorLayout : RelativeLayout {

    private var mPaint = Paint()
    private var mButton: Button? = null
    private var mView: Practice7ArgbEvaluatorView? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        mButton = findViewById(R.id.btn_start)
        mView = findViewById(R.id.view)


        mButton!!.setOnClickListener {
            val animator = ObjectAnimator.ofInt(
                    mView,
                    "color",
                    0xffff0000.toInt(),
                    0xff00ff00.toInt()
            )
            animator.setEvaluator(ArgbEvaluator())
            animator.interpolator = LinearInterpolator()
            animator.duration = 2000
            animator.start()
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}
