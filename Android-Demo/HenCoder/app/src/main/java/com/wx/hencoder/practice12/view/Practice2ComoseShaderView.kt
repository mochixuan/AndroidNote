package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.R
import com.wx.hencoder.utils.DisplayUtil

class Practice2ComoseShaderView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
        this.layerType
    }

    /*
    * https://juejin.im/post/596baf5f6fb9a06bb15a3df9
    * */

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果

        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.batman)
        val shader1 = BitmapShader(
                bitmap1,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
        )

        val bitmap2 = BitmapFactory.decodeResource(resources, R.mipmap.batman_logo)
        val shader2 = BitmapShader(
                bitmap2,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
        )

        /*
        * 两个叠加在一起
        * */
        val shader = ComposeShader(
                shader1,
                shader2,
                PorterDuff.Mode.DST_IN
        )
        mPaint.shader = shader

        canvas?.drawCircle(
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat(),
                DisplayUtil.dpTopx(context,150f).toFloat(),
                mPaint
        )
    }

}
