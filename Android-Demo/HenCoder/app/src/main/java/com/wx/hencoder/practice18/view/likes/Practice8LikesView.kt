package com.wx.hencoder.practice18.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.wx.hencoder.R
import com.wx.hencoder.practice18.view.likes.LikesImageView
import com.wx.hencoder.utils.DisplayUtil

class Practice8LikesView : LinearLayout , View.OnClickListener{

    private var mPaint = Paint()
    private var isLikes: Boolean = false
    private var likNums:Int = 158
    private var mLikesImageView:LikesImageView? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffcccccc")
        mPaint.textSize = DisplayUtil.dpTopx(context,16f)

        mLikesImageView = LikesImageView(context)

        orientation = LinearLayout.HORIZONTAL

        setOnClickListener(this)

        addView(mLikesImageView!!,getLikesImageViewParam())
    }

    fun getLikesImageViewParam():LayoutParams {
        val param = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        return param
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var widthAndHeight = Math.min(width,height)

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

        canvas?.drawText(
                likNums.toString(),
                gap*3+unselectBitmap.width,
                desHeight/2f+(-mPaint.descent()-mPaint.ascent())/2f,
                mPaint
        )


    }



    override fun onClick(p0: View?) {

        isLikes = !isLikes
        invalidate()
    }

}
