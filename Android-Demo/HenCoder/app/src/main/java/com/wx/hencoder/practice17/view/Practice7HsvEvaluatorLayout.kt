package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7HsvEvaluatorLayout : RelativeLayout {

    private var mPaint = Paint()
    private var mButton: Button? = null
    private var mView: Practice7HsvEvaluatorView? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        mButton = findViewById(R.id.btn_start)
        mView = findViewById(R.id.view)

        mButton?.setOnClickListener {
            val animator = ObjectAnimator.ofInt(
                    mView,
                    "color",
                    0xffff0000.toInt(),
                    0xff00ff00.toInt()
            )
            animator.setEvaluator(HsvEvaluator())
            animator.interpolator = LinearInterpolator()
            animator.duration = 2000
            animator.start()
        }

    }

    private inner class HsvEvaluator : TypeEvaluator<Int> {

        override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {

            val startHsv = FloatArray(3)
            val endHsv = FloatArray(3)
            val outHsv = FloatArray(3)

            Color.colorToHSV(startValue!!,startHsv)
            Color.colorToHSV(endValue!!,endHsv)

            if (endHsv[0] - startHsv[0] > 180) {
                endHsv[0] -= 360f
            } else if (endHsv[0] - startHsv[0] < -180) {
                endHsv[0] += 360f
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction
            if (outHsv[0] > 360) {
                outHsv[0] -= 360f
            } else if (outHsv[0] < 0) {
                outHsv[0] += 360f
            }

            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction

            val alpha = startValue shr 24 + ((endValue shr 24 - startValue shr 24) * fraction).toInt()

            return Color.HSVToColor(alpha, outHsv)
        }

    }

}
