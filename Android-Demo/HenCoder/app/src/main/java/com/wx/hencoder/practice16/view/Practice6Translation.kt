package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice6Translation : RelativeLayout {

    private var mPaint = Paint()
    private var button: Button? = null
    private var image: ImageView? = null
    private var state = 0
    private val stateCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 6 else 4

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        button = findViewById(R.id.btn_start)
        image = findViewById(R.id.imageView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            image?.outlineProvider = MusicOutLineProvider()
        }

        button?.setOnClickListener {
            when (state) {
                0 -> image!!.animate().translationX(DisplayUtil.dpTopx(context,200f))
                1 -> image!!.animate().translationX(DisplayUtil.dpTopx(context,0f))
                2 -> image!!.animate().translationY(DisplayUtil.dpTopx(context,150f))
                3 -> image!!.animate().translationY(DisplayUtil.dpTopx(context,0f))
                4 -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    image!!.animate().translationZ(DisplayUtil.dpTopx(context,15f))
                }
                5 -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    image!!.animate().translationZ(DisplayUtil.dpTopx(context,0f))
                }
            }
            state++
            if (state == stateCount) state = 0
        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    inner class MusicOutLineProvider : ViewOutlineProvider() {

        private val path = Path()

        init {
            {
                path.moveTo(0f, DisplayUtil.dpTopx(context,10f));
                path.lineTo(DisplayUtil.dpTopx(context,7f),DisplayUtil.dpTopx(context,2f))
                path.lineTo(DisplayUtil.dpTopx(context,116f),DisplayUtil.dpTopx(context,58f))
                path.lineTo(DisplayUtil.dpTopx(context,116f),DisplayUtil.dpTopx(context,70f))
                path.lineTo(DisplayUtil.dpTopx(context,7f),DisplayUtil.dpTopx(context,128f))
                path.lineTo(DisplayUtil.dpTopx(context,0f),DisplayUtil.dpTopx(context,120f))
                path.close();
            }
        }

        override fun getOutline(p0: View?, p1: Outline?) {

        }


    }

}
