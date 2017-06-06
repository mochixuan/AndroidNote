package com.wx.gaodemaster.interfaces

import android.app.Activity
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.model.Marker
import com.wx.gaodemaster.R
import com.wx.gaodemaster.databinding.ItemInfoWindowBinding

class MyInfoWindow: AMap.InfoWindowAdapter {

    var binding: ItemInfoWindowBinding? = null
    var activity: Activity? = null

    constructor(activity: Activity) {
        this.activity = activity
    }

    override fun getInfoContents(p0: Marker?): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker?): View {
        if(binding == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(activity),R.layout.item_info_window,null,false)
        }
        render(marker!!);
        return binding!!.root;
    }

    //如果想修改自定义Infow中内容，请通过view找到它并修改
    fun render(marker: Marker) {
        binding?.tvTitle?.text = marker.snippet
    }

}