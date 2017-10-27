package com.wx.hencoder.utils

import android.content.Context
import android.view.WindowManager

class DisplayUtil {

    companion object {

        fun dpTopx(context: Context,dpValue: Float): Float {
            return context.resources.displayMetrics.density*dpValue+0.5f;
        }

        fun pxTodp(context: Context,pxValue: Float): Float {
            return pxValue/context.resources.displayMetrics.density+0.5f;
        }

        fun getWidth(context: Context): Int {
            return (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
        }

        fun getHeight(context: Context): Int {
            return (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height
        }

    }

}