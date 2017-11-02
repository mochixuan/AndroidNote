package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice6MultiPropertier : RelativeLayout {

    private var mPaint = Paint()

    private var button: Button? = null
    private var image: ImageView? = null
    private var state = 0

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        button = findViewById(R.id.btn_start)
        image = findViewById(R.id.imageView)

        button?.setOnClickListener {
            when (state) {
                0 -> {
                    image!!.animate()
                            .translationX(DisplayUtil.dpTopx(context,200f))
                            .translationY(DisplayUtil.dpTopx(context,200f))
                            .rotation(360f)
                            .scaleX(1f)
                            .scaleY(1f)
                            .alpha(1f)
                }
                1 -> {
                    image!!.animate()
                            .translationX(DisplayUtil.dpTopx(context,0f))
                            .translationY(DisplayUtil.dpTopx(context,0f))
                            .rotation(0f)
                            .scaleX(0f)
                            .scaleY(0f)
                            .alpha(0f)
                }

            }
            state++
            if (state == 2) state = 0
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
