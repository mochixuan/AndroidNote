package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7OfObjectLayout : RelativeLayout {

    private var mButton: Button? = null
    private var mView: Practice7OfObjectView? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {



    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        mButton = findViewById(R.id.btn_start)
        mView = findViewById(R.id.view)

        mButton?.setOnClickListener {
            val animator = ObjectAnimator.ofObject(
                    mView,
                    "position",
                    PointFEvaluator(),
                    PointF(0f,0f),
                    PointF(1f,1f)
            )
            animator.interpolator = LinearInterpolator()
            animator.duration = 1000
            animator.start()
        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    private inner class PointFEvaluator : TypeEvaluator<PointF> {

        override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {

            val newPoint = PointF()

            val x = startValue!!.x+fraction*(endValue!!.x-startValue.x)
            val y = startValue!!.y+fraction*(endValue.y-startValue.y)

            newPoint.set(x,y)

            return newPoint

        }

    }

}
