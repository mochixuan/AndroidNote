package com.wx.hencoder.practice11.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7PropertyValuesHolderLayout : RelativeLayout {

    private var mPaint = Paint()

    private var image: ImageView? = null
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
            val holder1 = PropertyValuesHolder.ofFloat("scaleX",0f,1f)
            val holder2 = PropertyValuesHolder.ofFloat("scaleY",0f,1f)
            val holder3 = PropertyValuesHolder.ofFloat("alpha",0f,1f)
            ObjectAnimator.ofPropertyValuesHolder(image, holder1, holder2, holder3).start()
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}
