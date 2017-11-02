package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice6Duration : LinearLayout {

    private var mPaint = Paint()

    private var button: Button? = null
    private var image: ImageView? = null
    private var state = 0
    private var seekBar: AppCompatSeekBar? = null
    private var duration = 300

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        button = findViewById(R.id.btn_start)
        image = findViewById(R.id.imageView)
        seekBar = findViewById<AppCompatSeekBar>(R.id.seek_bar)
        seekBar!!.max = 3000
        seekBar!!.progress = 300
        seekBar!!.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                duration = p0!!.progress
            }

        })

        button!!.setOnClickListener {

            when (state) {
                0 -> image!!.animate().translationX(DisplayUtil.dpTopx(context,100f)).setDuration(duration.toLong())
                1 -> image!!.animate().translationX(DisplayUtil.dpTopx(context,0f)).setDuration(duration.toLong())
            }

            state++

            if (state==2) state = 0

        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
