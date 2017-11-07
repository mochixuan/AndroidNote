package com.wx.hencoder.practice18.view.xmmotion

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.wx.hencoder.utils.DisplayUtil

class Practice8XmMotionView : RelativeLayout {

    private var mPaint = Paint()
    private var mRotatingRingView: RotatingRingView? = null

    private var titleTextSize = DisplayUtil.spTopx(context,40f)
    private var sm1titleTextSize = DisplayUtil.spTopx(context,12f)
    private var sm2titleTextSize = DisplayUtil.spTopx(context,12f)

    private var title = 5788
    private var sm1title = "1.7公里"
    private var sm2title = "34千卡"

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {


        init()
    }

    private fun init() {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffffffff")

        mRotatingRingView = RotatingRingView(context)
        mRotatingRingView!!.layoutParams = getRotatingRingParams()
        addView(mRotatingRingView)

    }

    fun getRotatingRingParams(): LayoutParams {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        return params
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //大标题
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.textSize = titleTextSize
        mPaint.isFakeBoldText = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.letterSpacing = 0.1f
        }
        val titleY = measuredHeight/2f+(-mPaint.descent()-mPaint.ascent())/2f
        canvas?.drawText(
                title.toString(),
                measuredWidth/2f,
                titleY,
                mPaint
        )

        val gapWidth = DisplayUtil.dpTopx(context,4f)
        val gapHeight = measuredHeight/20f

        //一号标题
        mPaint.textSize = sm1titleTextSize
        mPaint.isFakeBoldText = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.letterSpacing = 0f
        }

    }

}
