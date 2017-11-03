package com.wx.hencoder.practice11.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wx.hencoder.R

class Practice7AnimatorSetLayout : RelativeLayout {

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

        /*
        * http://blog.csdn.net/io_field/article/details/53101295
        * */

        button!!.setOnClickListener {

            image?.translationX = 0f

            val animator1 = ObjectAnimator.ofFloat(image,"alpha",0f,1f)
            val animator2 = ObjectAnimator.ofFloat(image,"translationX",0f,400f)
            val animator3 = ObjectAnimator.ofFloat(image,"rotation",0f,1080f)

            val animatorSet = AnimatorSet()
            animatorSet.play(animator1).before(animator2)
            animatorSet.playTogether(animator2,animator3)
            animatorSet.duration = 3000

            animatorSet.start()

        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



    }

}
