package com.wx.gaodemaster.actvity

import android.databinding.ViewDataBinding
import android.location.Location
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import com.amap.api.maps.*
import com.amap.api.maps.model.*
import com.amap.api.services.core.AMapException.CODE_AMAP_SUCCESS
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.wx.gaodemaster.R
import com.wx.gaodemaster.base.BaseActivity
import com.wx.gaodemaster.bean.MarkerBean
import com.wx.gaodemaster.databinding.ActivityMapBinding
import com.wx.gaodemaster.interfaces.MyInfoWindow
import com.wx.gaodemaster.overlay.WalkRouteOverlay
import com.wx.gaodemaster.utils.MarkerUtil
import java.util.*



class MapActivity : BaseActivity() {

    lateinit var binding: ActivityMapBinding
    lateinit var mMap: AMap
    lateinit var mUiSettings: UiSettings
    var mAnimationSet: AnimationSet? = null
    var mLatLng: LatLng? = null
    var isInitLocation: Boolean? = null

    lateinit var mRouteSearch: RouteSearch
    lateinit var mWalkRouteResult: WalkRouteResult
    var walkRouteOverlay: WalkRouteOverlay? = null

    override fun getLayouId(): Int {
        return R.layout.activity_map
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMapBinding
    }

    override fun initData() {
        //创建地图
        binding.mapView.onCreate(savedInstanceState)
        //获取操作类
        mMap = binding.mapView.map
        //基本设置
        mUiSettings = mMap.uiSettings
        //初始化按钮
        initListener()
        //路线规划
        mRouteSearch = RouteSearch(this)
        mRouteSearch.setRouteSearchListener(routeListener)
        //自定义infiWindow
        mMap.setInfoWindowAdapter(MyInfoWindow(this))
        //设置ToolBar
        setSupportActionBar(binding.toolBar)

    }

    private fun initListener() {

        //初始化基本设置
        initSetting()

        //定位当前位置，以我为中心
        binding.fabLocation.setOnClickListener(View.OnClickListener {
            locationSelf()
        })

        mMap.setOnMyLocationChangeListener(object : AMap.OnMyLocationChangeListener {
            override fun onMyLocationChange(p0: Location?) {
                if (p0 != null) {
                    mLatLng = LatLng(p0.latitude, p0.longitude)
                }
                if (!isInitLocation!! && mLatLng != null) {
                    isInitLocation = true
                    locationSelf()
                    initMarker(mLatLng!!)
                }
            }
        })

        //单击事件
        mMap.setOnMarkerClickListener(object : AMap.OnMarkerClickListener {
            override fun onMarkerClick(p0: Marker?): Boolean {
                planRoute(mLatLng!!, p0!!.position)
                return false
            }
        })

    }

    //初始化标记
    fun initMarker(latLng: LatLng) {
        var options: ArrayList<MarkerOptions> = arrayListOf()
        for (i in 1..20) {
            var alat: LatLng
            if (i % 7 == 0) {
                alat = LatLng(latLng.latitude + 0.0002 * i, latLng.longitude - 0.0002 * i)
            } else if (i % 6 == 0) {
                alat = LatLng(latLng.latitude - 0.0003 * i, latLng.longitude + 0.0001 * i)
            } else if (i % 5 == 0) {
                alat = LatLng(latLng.latitude + 0.0001 * i, latLng.longitude - 0.0003 * i)
            } else if (i % 4 == 0) {
                alat = LatLng(latLng.latitude + 0.0003 * i, latLng.longitude + 0.0001 * i)
            } else if (i % 3 == 0) {
                alat = LatLng(latLng.latitude - 0.0001 * i, latLng.longitude - 0.0002 * i)
            } else if (i % 2 == 0) {
                alat = LatLng(latLng.latitude - 0.0002 * i, latLng.longitude + 0.0003 * i)
            } else {
                alat = LatLng(latLng.latitude - 0.0002 * i, latLng.longitude + 0.0001 * i)
            }
            val option: MarkerOptions = MarkerUtil.addMarkers(MarkerBean("bike" + i, "Bike" + i, alat, true, R.mipmap.icon_marker1))
            options.add(option)
        }
        mMap.addMarkers(options, true)
    }

    //规划路线
    fun planRoute(startPosition: LatLng, endPosition: LatLng) {
        val start: LatLonPoint = LatLonPoint(startPosition.latitude, startPosition.longitude)
        val end: LatLonPoint = LatLonPoint(endPosition.latitude, endPosition.longitude)

        var fromAndTo: RouteSearch.FromAndTo = RouteSearch.FromAndTo(start, end)
        val walkRoute: RouteSearch.WalkRouteQuery = RouteSearch.WalkRouteQuery(fromAndTo)

        mRouteSearch.calculateWalkRouteAsyn(walkRoute)
    }

    //路线
    val routeListener: RouteSearch.OnRouteSearchListener = object : RouteSearch.OnRouteSearchListener {
        override fun onBusRouteSearched(busRouteResult: BusRouteResult, i: Int) {

        }

        override fun onDriveRouteSearched(driveRouteResult: DriveRouteResult, i: Int) {

        }

        override fun onWalkRouteSearched(result: WalkRouteResult, errorCode: Int) {

            if (errorCode == CODE_AMAP_SUCCESS && result?.paths?.size!! > 0) {
                mWalkRouteResult = result
                val walkPath = mWalkRouteResult.paths[0]
                if (walkRouteOverlay != null) {
                    walkRouteOverlay?.removeFromMap()
                }
                walkRouteOverlay = WalkRouteOverlay(this@MapActivity, mMap, walkPath, mWalkRouteResult.startPos, mWalkRouteResult.targetPos)
                walkRouteOverlay?.setNodeIconVisibility(false)  //不显示基本图标
                walkRouteOverlay?.addToMap()
                walkRouteOverlay?.zoomToSpan()
            }
        }

        override fun onRideRouteSearched(rideRouteResult: RideRouteResult, i: Int) {

        }
    }

    //可能有好的方法，但这一期5.1.0文档真没找到，有点坑人
    fun locationSelf() {
        if (mLatLng != null) {
            val cameraUpdate: CameraUpdate
            cameraUpdate = CameraUpdateFactory.changeLatLng(mLatLng)
            mMap.animateCamera(cameraUpdate)
            refreshUi()
        }
    }

    //应该是请求结束再加动画，这里不讨论
    fun refreshUi() {
        binding.fabRefresh.clearAnimation()
        if (mAnimationSet == null) {
            mAnimationSet = AnimationSet(true)
            val rotate: Animation = RotateAnimation(0f, 360f * 4,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 2000
            val alpha: Animation = AlphaAnimation(1f, 0f)
            alpha.duration = 2000
            //kotlin 出错了？
            mAnimationSet!!.addAnimation(rotate)
            mAnimationSet!!.addAnimation(alpha)
            mAnimationSet!!.fillAfter = true
        }
        binding.fabRefresh.startAnimation(mAnimationSet)
        mAnimationSet!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.fabRefresh.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.fabRefresh.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

    //定位到当前位置
    fun initSetting() {
        //放大缩小按钮隐藏
        mUiSettings.isZoomControlsEnabled = false
        //显示比例尺
        mUiSettings.isScaleControlsEnabled = true
        //设置高德图标位置
        mUiSettings.logoPosition = AMapOptions.LOGO_POSITION_BOTTOM_RIGHT
        //设置初始化缩放比例(自己对下比例，自带定位功能)
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17f))

        val mLocationStyle: MyLocationStyle = MyLocationStyle()
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效
        mLocationStyle.interval(2000)
        //自定义定位蓝点图标
        val icon: BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_dot)
        mLocationStyle.myLocationIcon(icon)
        //设置是否显示定位小蓝点
        mLocationStyle.showMyLocation(true)
        //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        mLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        //设置定位蓝点图标的锚点方法中心
        mLocationStyle.anchor(0.5f, 0.5f)
        //设置定位蓝点精度圆圈的边框颜色的方法    //需要添加兼容
        mLocationStyle.strokeColor(resources.getColor(R.color.blue))
        //设置定位蓝点精度圆圈的填充颜色的方法
        mLocationStyle.radiusFillColor(resources.getColor(R.color.blue_aphal))
        mMap.myLocationStyle = mLocationStyle
        // 设置为true表示启动显示定位蓝点
        mMap.isMyLocationEnabled = true
        //显示定位按钮
        //mMap.uiSettings.isMyLocationButtonEnabled = true
    }

    //------生命周期------

    override fun onResume() {
        super.onResume()
        //重绘地图
        binding.mapView.onResume()
        isInitLocation = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //保持地图
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        //暂停地图
        binding.mapView.onPause()
        isInitLocation = true
    }

    override fun onDestroy() {
        super.onDestroy()
        //摧毁地图
        binding.mapView.onDestroy()
    }

}
