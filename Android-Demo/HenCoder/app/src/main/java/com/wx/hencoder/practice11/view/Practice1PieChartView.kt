package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1PieChartView : View {

    private var mPaint = Paint()
    private var mTags = arrayOf("Marshmallow","Froyo","Gingerbread","Ice Cream Sandwich","Jelly Bean","KitKat","Lollipop")
    private var mColors = arrayOf("#ffffff00","#ffff3300","#ffff00ff","#ff6600ff","#ff6A5ACD","#ffFF7F50","#ffff0033")
    private var mRatios = arrayOf(40,5,20,15,45,110,125)
    private var width: Int? = DisplayUtil.getWidth(context)

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mRatios.forEachIndexed { index, item ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                var gap = 0;
                for (i in 0..index-1) {
                    gap+=mRatios[i]
                }


                mPaint.color = Color.WHITE
                mPaint.strokeWidth = 4f
                var centerAngle = (-(-45f+gap+item/2f-1)+360)%360
                var flagX = 1;
                var flagY = 1;
                if ((centerAngle/90).toInt()>=0 && (centerAngle/90).toInt()<1) {
                    flagY = -1
                } else if ((centerAngle/90).toInt()>=1 && (centerAngle/90).toInt()<2) {
                    flagY = -1
                } else if ((centerAngle/90).toInt()>=2 && (centerAngle/90).toInt()<3) {
                    flagY = -1
                } else if ((centerAngle/90).toInt()>=3 && (centerAngle/90).toInt()<4) {

                }

                canvas?.drawLine(
                        (width!!/2+DisplayUtil.dpTopx(context,104f)*Math.cos(Math.PI*centerAngle/180f)).toFloat(),
                        (DisplayUtil.dpTopx(context,150f)-DisplayUtil.dpTopx(context,104f)*Math.sin(Math.PI*centerAngle/180f)).toFloat(),
                        (width!!/2+DisplayUtil.dpTopx(context,120f)*Math.cos(Math.PI*centerAngle/180f)).toFloat(),
                        (DisplayUtil.dpTopx(context,150f)-DisplayUtil.dpTopx(context,120f)*Math.sin(Math.PI*centerAngle/180f)).toFloat(),
                        mPaint
                )

                if (Math.cos(Math.PI*centerAngle/180f)>0) {

                }

                var linehendY = (width!!/2+DisplayUtil.dpTopx(context,120f)*Math.cos(Math.PI*centerAngle/180f)).toFloat()+(if (Math.cos(Math.PI*centerAngle/180f)>0) DisplayUtil.dpTopx(context,20f) else -DisplayUtil.dpTopx(context,20f))

                canvas?.drawLine(
                        (width!!/2+DisplayUtil.dpTopx(context,120f)*Math.cos(Math.PI*centerAngle/180f)).toFloat(),
                        (DisplayUtil.dpTopx(context,150f)-DisplayUtil.dpTopx(context,120f)*Math.sin(Math.PI*centerAngle/180f)).toFloat(),
                        linehendY,
                        (DisplayUtil.dpTopx(context,150f)-DisplayUtil.dpTopx(context,120f)*Math.sin(Math.PI*centerAngle/180f)).toFloat(),
                        mPaint
                )

                var devi = 0f;
                if (index == mRatios.size-1) {
                    devi = -DisplayUtil.dpTopx(context,1f);
                }



                mPaint.color = Color.parseColor(mColors.get(index))
                canvas?.drawArc(
                        width!!/2-DisplayUtil.dpTopx(context,100f)+devi,
                        DisplayUtil.dpTopx(context,50f)+devi,
                        width!!/2+DisplayUtil.dpTopx(context,100f)+devi,
                        DisplayUtil.dpTopx(context,250f)+devi,
                        -45f+gap,
                        item.toFloat()-2,
                        true,
                        mPaint
                )
            }
        }

    }

}
