package com.wx.gaodemaster

import android.Manifest
import android.databinding.ViewDataBinding
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wx.gaodemaster.actvity.MapActivity
import com.wx.gaodemaster.base.BaseActivity
import com.wx.gaodemaster.databinding.ActivityMainBinding
import io.reactivex.functions.Consumer

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun getLayouId(): Int {
        return R.layout.activity_main
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding;
    }

    override fun initData() {
        initPermissions()
        binding.btnMap.setOnClickListener(View.OnClickListener {
            openActivity(MapActivity::class.java)
        })
    }

    fun initPermissions() {
        val rxPermission: RxPermissions = RxPermissions(this)
        rxPermission.requestEach(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(Consumer {
                    if (it.granted) {   // 用户已经同意该权限

                    } else if (it.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        showToast("请授予权限")
                        finish()
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        showToast("请在权限界面授予权限")
                        finish()
                    }
                })

    }

}
