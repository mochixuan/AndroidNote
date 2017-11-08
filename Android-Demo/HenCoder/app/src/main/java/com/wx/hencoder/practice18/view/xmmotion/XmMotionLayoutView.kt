package com.wx.hencoder.practice18.view.xmmotion

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class XmMotionLayoutView : RelativeLayout {

    private var mPaint = Paint()

    private var mSolidRingView: SolidRingView? = null
    private var mNoConnRingView: NoConnRingView? = null
    private var mConnRingView: ConnRingView? = null

    private var title = ""
    private var sm1title = ""
    private var sm2title = ""

    private var titleTextSize = 0f
    private var sm1titleTextSize = 0f
    private var sm2titleTextSize = 0f

    private var iconResId = 0
    private var iconSize = 0f
    private var mRingProgress = 0

    private var mState: ConnectState = ConnectState.NOCONNECT

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.XmMotionLayoutView)

        try {
            title = typedArray.getString(R.styleable.XmMotionLayoutView_title)
            sm1title = typedArray.getString(R.styleable.XmMotionLayoutView_sm1title)
            sm2title = typedArray.getString(R.styleable.XmMotionLayoutView_sm2title)
        } catch (e:Exception) {
            e.printStackTrace()
        }

        titleTextSize = typedArray.getDimension(R.styleable.XmMotionLayoutView_titleTextSize,DisplayUtil.spTopx(context,56f))
        sm1titleTextSize = typedArray.getDimension(R.styleable.XmMotionLayoutView_sm1titleSize,DisplayUtil.spTopx(context,14f))
        sm2titleTextSize = typedArray.getDimension(R.styleable.XmMotionLayoutView_sm2titleSize,DisplayUtil.spTopx(context,14f))

        iconResId = typedArray.getResourceId(R.styleable.XmMotionLayoutView_iconResId,0)
        iconSize = typedArray.getDimension(R.styleable.XmMotionLayoutView_iconSize,DisplayUtil.dpTopx(context,24f))
        mRingProgress = typedArray.getInteger(R.styleable.XmMotionLayoutView_ringProgress,0)

        typedArray.recycle()

        init()
    }

    private fun init() {
        mPaint.isAntiAlias = true //抗锯齿
        mPaint.color = Color.parseColor("#ffffffff")

        mSolidRingView = SolidRingView(context)
        mSolidRingView!!.layoutParams = getParams()
        addView(mSolidRingView)

        mNoConnRingView = NoConnRingView(context)
        mNoConnRingView!!.layoutParams = getParams()
        addView(mNoConnRingView)

        mConnRingView = ConnRingView(context)
        mConnRingView!!.layoutParams = getParams()
        addView(mConnRingView)

        setConnectState(mState)
    }

    fun getParams(): LayoutParams {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        return params
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCenterContent(canvas!!)
    }


    private fun drawCenterContent(canvas: Canvas) {
        mPaint.strokeWidth = 0f
        mPaint.alpha = 255

        //大标题
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.textSize = titleTextSize
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.letterSpacing = 0.1f
        }
        val titleY = measuredHeight/2f+(-mPaint.descent()-mPaint.ascent())/2f
        canvas.drawText(
                title,
                measuredWidth/2f,
                titleY,
                mPaint
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.letterSpacing = 0f
        }

        val gapWidth = DisplayUtil.dpTopx(context,4f)
        val gapHeight = measuredHeight/20f
        mPaint.textSize = sm1titleTextSize
        if (sm1titleTextSize<sm2titleTextSize) {
            mPaint.textSize = sm2titleTextSize
        }
        val smTitleY = (-mPaint.ascent()-mPaint.descent()) + gapHeight+titleY

        mPaint.textAlign = Paint.Align.RIGHT
        mPaint.textSize = sm1titleTextSize
        canvas.drawText(sm1title,measuredWidth/2f-gapWidth,smTitleY,mPaint)

        mPaint.textAlign = Paint.Align.LEFT
        mPaint.textSize = sm2titleTextSize
        canvas.drawText(sm2title,measuredWidth/2f+gapWidth,smTitleY,mPaint)

        if (sm1title.length != 0 && sm2title.length != 0) {
            mPaint.strokeWidth = DisplayUtil.dpTopx(context,1f)
            canvas.drawLine(measuredWidth/2f,gapHeight+titleY,measuredWidth/2f,smTitleY,mPaint)
        }

        if (iconResId > 0) {
            var icon = BitmapFactory.decodeResource(resources,iconResId)
            val scale = iconSize.toFloat()/icon.width
            val matrix = Matrix()
            matrix.postScale(scale,scale)
            icon = Bitmap.createBitmap(icon,0,0,icon.width,icon.height,matrix,true)
            canvas.drawBitmap(icon,(measuredWidth-icon.width)/2f,smTitleY+gapHeight,mPaint)
        }

    }

    fun setRingProgress(ringProgress:Int) {
        mSolidRingView!!.setRingProgress(ringProgress,true)
    }
    fun getRingProgress():Int {
        return mSolidRingView!!.getRingProgress()
    }

    private var scale: Float = 1f
    private fun setScale(scale: Float) {
        this.scale = scale
        mSolidRingView!!.scaleX = scale
        mSolidRingView!!.scaleY = scale
        mConnRingView!!.scaleX = scale
        mConnRingView!!.scaleY = scale
    }


    fun setConnectState(state: ConnectState) {
        this.mState = state
        if (state == ConnectState.CONNECT) {
            mNoConnRingView!!.visibility = View.GONE
            mConnRingView!!.visibility = View.VISIBLE
            mSolidRingView!!.visibility = View.VISIBLE
            mNoConnRingView!!.onEndAnim()
            mConnRingView!!.onStartAnim()
            mSolidRingView!!.setRingProgress(mRingProgress,true)
        } else {
            mNoConnRingView!!.visibility = View.VISIBLE
            mConnRingView!!.visibility = View.GONE
            mSolidRingView!!.visibility = View.GONE
            mConnRingView!!.onEndAnim()
            mNoConnRingView!!.onStartAnim()
        }
        if (state == ConnectState.CONNECT) {
            scale = 0.8f
            val keyframe1 = Keyframe.ofFloat(0f,0.8f)
            val keyframe2 = Keyframe.ofFloat(0.8f,1.1f)
            val keyframe3 = Keyframe.ofFloat(1f,1f)

            val holder = PropertyValuesHolder.ofKeyframe("scale",keyframe1,keyframe2,keyframe3)
            val animator = ObjectAnimator.ofPropertyValuesHolder(this,holder)
            animator.duration = 600
            animator.start()

        }
    }

    fun getConnectState(): ConnectState {
        return mState
    }

}
