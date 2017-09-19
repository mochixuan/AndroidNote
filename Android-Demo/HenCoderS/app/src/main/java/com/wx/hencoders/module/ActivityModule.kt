package com.wx.hencoders.module

import android.app.Activity

data class ActivityModule(var activity: Class<out Activity>,var data: String) {

}
