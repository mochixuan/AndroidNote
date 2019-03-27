package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class Practice1ColorView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //canvas?.drawColor(Color.BLACK)
        //canvas?.drawColor(ContextCompat.getColor(context, R.color.colorAccent))
        canvas?.drawColor(Color.parseColor("#ffff0066"))
    }

}
