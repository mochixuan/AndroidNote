package com.wx.uimaster.widget.banner

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder

class LocalImageHolderView : Holder<Int> {

    private var imageView: ImageView? = null

    override fun UpdateUI(context: Context?, position: Int, data: Int?) {
        imageView!!.setImageResource(data!!)
    }

    override fun createView(context: Context?): View {
        imageView = ImageView(context)
        imageView!!.scaleType = ImageView.ScaleType.FIT_XY
        return imageView as ImageView
    }

}
