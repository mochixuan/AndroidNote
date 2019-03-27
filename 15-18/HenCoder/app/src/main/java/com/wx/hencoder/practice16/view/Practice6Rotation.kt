package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice6Rotation : RelativeLayout {

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
                0 -> image!!.animate().rotation(180f)
                1 -> image!!.animate().rotation(0f)
                2 -> image!!.animate().rotationX(180f)
                3 -> image!!.animate().rotationX(0f)
                4 -> image!!.animate().rotationY(180f)
                5 -> image!!.animate().rotationY(0f)
            }
            state++
            if (state == 6) state = 0
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
