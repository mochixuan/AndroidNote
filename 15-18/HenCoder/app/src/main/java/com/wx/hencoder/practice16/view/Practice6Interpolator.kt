package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v4.view.animation.PathInterpolatorCompat
import android.util.AttributeSet
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice6Interpolator : LinearLayout {

    private var mPaint = Paint()
    private var spinner: Spinner? = null
    private var button: Button? = null
    private var image: ImageView? = null
    private var state = 0
    private var interpolators = arrayOfNulls<Interpolator>(13)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val interpolatorPath = Path()
        interpolatorPath.lineTo(0.25f,0.25f)
        interpolatorPath.moveTo(0.25f,1.5f)
        interpolatorPath.lineTo(1f,1f)

        interpolators[0] = AccelerateDecelerateInterpolator()
        interpolators[1] = LinearInterpolator()
        interpolators[2] = AccelerateInterpolator()
        interpolators[3] = DecelerateInterpolator()
        interpolators[4] = AnticipateInterpolator()
        interpolators[5] = OvershootInterpolator()
        interpolators[6] = AnticipateOvershootInterpolator()
        interpolators[7] = BounceInterpolator()
        interpolators[8] = CycleInterpolator(0.5f)
        interpolators[9] = PathInterpolatorCompat.create(interpolatorPath)
        interpolators[10] = FastOutLinearInInterpolator()
        interpolators[11] = FastOutSlowInInterpolator()
        interpolators[12] = LinearOutSlowInInterpolator()

        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.btn_start)
        image = findViewById(R.id.imageView)

        button!!.setOnClickListener {
            image!!.animate()
                    .translationX(DisplayUtil.dpTopx(context,150f))
                    .setDuration(600)
                    .setInterpolator(interpolators[spinner!!.selectedItemPosition])
                    .withEndAction(Runnable {
                        image!!.postDelayed(Runnable {
                            image!!.translationX = 0f
                        },500)
                    })
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
