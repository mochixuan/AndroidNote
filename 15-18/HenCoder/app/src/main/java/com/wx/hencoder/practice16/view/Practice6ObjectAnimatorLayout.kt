package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice6ObjectAnimatorLayout : RelativeLayout {

    private var mPaint = Paint()
    private var button:Button? = null
    private var image:Practice6ObjectAnimatorView? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        button = findViewById(R.id.btn_start)
        image = findViewById(R.id.imageView)

        button!!.setOnClickListener {

            var newProgress = image!!.getProgress()
            newProgress+=10
            if (newProgress>100) {
                newProgress = 0f
            }

            val animator = ObjectAnimator.ofFloat(image!!,"progress",image!!.getProgress(),newProgress)
            animator.duration = 1000
            animator.interpolator = AccelerateInterpolator()
            animator.start()

        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}
