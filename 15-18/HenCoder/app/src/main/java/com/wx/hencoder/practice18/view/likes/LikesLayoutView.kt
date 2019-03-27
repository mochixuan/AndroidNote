package com.wx.hencoder.practice18.view

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.wx.hencoder.R
import com.wx.hencoder.practice18.view.likes.LikesImageView
import com.wx.hencoder.practice18.view.likes.LikesTextView
import com.wx.hencoder.utils.DisplayUtil

class LikesLayoutView : LinearLayout , View.OnClickListener{

    private var mPaint = Paint()
    private var mLikesImageView:LikesImageView? = null
    private var mLikesTextView:LikesTextView? = null

    private var textColor: Int? = null
    private var textSize: Float? = null
    private var isLikes: Boolean = false
    private var likesNum: Int = 0
    private var mDefaultGapWidth = 0
    private var mDefaultGapHeight = 0

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.LikesLayoutView)
        textColor = typedArray.getColor(R.styleable.LikesLayoutView_textColor,Color.parseColor("#ffcccccc"))
        textSize = typedArray.getDimension(R.styleable.LikesLayoutView_textSize,DisplayUtil.dpTopx(context!!,16f))
        isLikes = typedArray.getBoolean(R.styleable.LikesLayoutView_isLikes,false)
        likesNum = typedArray.getInteger(R.styleable.LikesLayoutView_likesNum,0)
        typedArray.recycle()

        init()
    }

    private fun init() {

        mDefaultGapWidth = DisplayUtil.dpTopx(context,15f).toInt()
        mDefaultGapHeight = DisplayUtil.dpTopx(context,4f).toInt()

        mLikesImageView = LikesImageView(context)
        mLikesImageView!!.setIsLikes(isLikes)


        mLikesTextView = LikesTextView(context)
        mLikesTextView!!.setTextColor(textColor!!)
        mLikesTextView!!.setTextSize(textSize!!)
        mLikesTextView!!.setIsLikes(isLikes)
        mLikesTextView!!.setCenterNums(likesNum)

        orientation = LinearLayout.HORIZONTAL

        setOnClickListener(this)

        addView(mLikesImageView!!,getLikesImageViewParam())
        addView(mLikesTextView!!,getLikesTextViewParam())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var minWidth = mDefaultGapWidth
        var minHeight = mDefaultGapHeight
        for (i in 0..childCount-1) {
            minWidth = minWidth + getChildAt(i).measuredWidth
            minHeight = minHeight + getChildAt(i).measuredHeight
        }
        setMeasuredDimension(getMeasureSize(widthMeasureSpec,minWidth),getMeasureSize(heightMeasureSpec,minHeight))
    }

    fun getMeasureSize(measureSpec: Int,minValue: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        var realSize = minValue
        when (specMode) {
            //相当于wrap_content (等于或小于这个能够给予的最大值)
            MeasureSpec.AT_MOST -> {

            }
            //为math_parent或者固定值时
            MeasureSpec.EXACTLY -> {
                realSize = specSize
            }
            //没有确定值或为0这个和EXACTLY一样处理一般，想要多大就多大
            MeasureSpec.UNSPECIFIED -> {

            }
        }
        return realSize
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        val child0 = getChildAt(0)
        val child1 = getChildAt(1)
        val realAllChildWidth = child0.measuredWidth+child1.measuredWidth
        val mAllWidthGap = measuredWidth - realAllChildWidth
        val child0Right = mAllWidthGap*2/3+child0.width
        child0.layout(0, 0, child0Right, measuredHeight)
        child1.layout(child0Right, 0, measuredWidth, measuredHeight)
    }

    fun getLikesImageViewParam():LayoutParams {
        val param = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        return param
    }

    fun getLikesTextViewParam():LayoutParams {
        val param = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        return param
    }

    override fun onClick(p0: View?) {
        isLikes = !isLikes
        mLikesImageView!!.onAnimator()
        mLikesTextView!!.onAnimator()
    }

}
