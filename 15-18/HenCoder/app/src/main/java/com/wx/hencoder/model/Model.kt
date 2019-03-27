package com.wx.hencoder.model

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes

data class PageModel(@LayoutRes val layoutId: Int, @StringRes val titleRes: Int)
