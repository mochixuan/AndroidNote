package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice8LikesView : View , View.OnClickListener{

    private var mPaint = Paint()
    private var isLikes: Boolean = false
    private var likNums:Int = 158

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffcccccc")
        mPaint.textSize = DisplayUtil.dpTopx(context,16f)
        setOnClickListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val unselectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_comment_like)
        val selectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected)
        val selectShiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected_shining)

        var desWidth = width
        var desHeight = height
        var minWidth = unselectBitmap.width+mPaint.measureText(likNums.toString())
        var minHeight = 0

        if (width<minWidth) {
            desWidth = minWidth.toInt()
        }
        if (height<minHeight) {
            desHeight = minHeight
        }

        minHeight = unselectBitmap.height

        val gap = (desWidth-minWidth)/5f

        if (isLikes) {

            canvas?.drawBitmap(
                    selectShiningBitmap,
                    gap*2+3,
                    desHeight/2f-selectBitmap.height+3,
                    mPaint
            )

            canvas?.drawBitmap(
                    selectBitmap,
                    gap*2,
                    desHeight/2f-selectBitmap.height/2f,
                    mPaint
            )

        } else {

            canvas?.drawBitmap(
                    unselectBitmap,
                    gap*2,
                    desHeight/2f-unselectBitmap.height/2f,
                    mPaint
            )

        }

        canvas?.drawText(
                likNums.toString(),
                gap*3+unselectBitmap.width,
                desHeight/2f+(-mPaint.descent()-mPaint.ascent())/2f,
                mPaint
        )

    }

    fun drawText(canvas: Canvas,startX:Int,isWantAnim: Boolean) {

    }

    override fun onClick(p0: View?) {

        isLikes = !isLikes
        invalidate()
    }

}
