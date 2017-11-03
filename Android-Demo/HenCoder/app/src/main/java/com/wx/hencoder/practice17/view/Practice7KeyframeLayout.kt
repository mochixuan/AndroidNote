package com.wx.hencoder.practice11.view

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.widget.Button
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7KeyframeLayout : RelativeLayout {

    private var mPaint = Paint()

    private var image: Practice7KeyframeView? = null
    private var button: Button? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        image = findViewById(R.id.imageview)
        button = findViewById(R.id.btn_start)

        button!!.setOnClickListener {

            val keyframe1 = Keyframe.ofFloat(0f,0f)
            val keyframe2 = Keyframe.ofFloat(0.5f,100f)
            val keyframe3 = Keyframe.ofFloat(1f,80f)

            val holder = PropertyValuesHolder.ofKeyframe(
                    "progress",
                    keyframe1,
                    keyframe2,
                    keyframe3
            )

            val animator = ObjectAnimator.ofPropertyValuesHolder(image,holder)

            animator.interpolator = FastOutSlowInInterpolator()
            animator.duration = 2000
            animator.start()

        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}
