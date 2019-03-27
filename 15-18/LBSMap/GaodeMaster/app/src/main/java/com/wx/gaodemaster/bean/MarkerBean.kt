package com.wx.gaodemaster.bean

import com.amap.api.maps.model.LatLng

data class MarkerBean(var title:String,var snippet:String,
                      var position: LatLng,var isDraggable: Boolean,
                      var icon: Int) {
}