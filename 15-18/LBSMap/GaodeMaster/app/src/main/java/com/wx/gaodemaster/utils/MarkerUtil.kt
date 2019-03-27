package com.wx.gaodemaster.utils

import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.MarkerOptions
import com.wx.gaodemaster.bean.MarkerBean

class MarkerUtil {

    companion object {
        fun addMarkers(markerBean: MarkerBean): MarkerOptions {
            val option: MarkerOptions = MarkerOptions()
            option.position(markerBean.position)
                    .title(markerBean.title)
                    .snippet(markerBean.snippet)
                    .draggable(markerBean.isDraggable)
                    .icon(BitmapDescriptorFactory.fromResource(markerBean.icon))
                    .setFlat(true)
            return option
        }
    }

}
