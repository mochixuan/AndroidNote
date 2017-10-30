package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice3StaticLayoutView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val textPaint = TextPaint()
        textPaint.textSize = DisplayUtil.dpTopx(context,18f)

        val text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
        val staticLayout1 = StaticLayout(
                text1,
                textPaint,
                600,
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                true
        )

        val text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz"
        val staticLayout2 = StaticLayout(text2, textPaint, 600,
                Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true)

        canvas?.save()
        canvas?.translate(50f,100f)
        staticLayout1.draw(canvas)

        canvas?.translate(0f,200f)
        staticLayout2.draw(canvas)
        canvas?.restore()

    }

}
