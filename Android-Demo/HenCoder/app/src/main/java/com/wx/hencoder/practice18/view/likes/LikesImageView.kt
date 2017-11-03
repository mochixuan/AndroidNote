package com.wx.hencoder.practice18.view.likes

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R


class LikesImageView : View {

    private var mPaint = Paint()
    private var isLikes: Boolean = false

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val unselectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_comment_like)
        val selectBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected)
        val selectShiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected_shining)

        var desWidth = width
        var desHeight = height

        var minHeight = 0

        /*if (width<minWidth) {
            desWidth = minWidth.toInt()
        }
        if (height<minHeight) {
            desHeight = minHeight
        }*/

        minHeight = unselectBitmap.height



        if (isLikes) {

            canvas?.drawBitmap(
                    selectShiningBitmap,
                    3f,
                    desHeight/2f-selectBitmap.height+3,
                    mPaint
            )

            canvas?.drawBitmap(
                    selectBitmap,
                    0f,
                    desHeight/2f-selectBitmap.height/2f,
                    mPaint
            )

        } else {

            canvas?.drawBitmap(
                    unselectBitmap,
                    0f,
                    desHeight/2f-unselectBitmap.height/2f,
                    mPaint
            )

        }


    }

}
