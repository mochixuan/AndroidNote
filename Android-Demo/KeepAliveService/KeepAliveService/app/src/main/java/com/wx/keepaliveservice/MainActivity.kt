package com.wx.keepaliveservice

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.wx.keepaliveservice.databinding.ActivityMainBinding
import com.wx.keepaliveservice.onepx.LiveActivity
import com.wx.keepaliveservice.service.BaseService
import com.wx.keepaliveservice.service.MyJobSheduler
import com.wx.keepaliveservice.service.NotificationService
import com.wx.keepaliveservice.serviceone.ForeService

class MainActivity : AppCompatActivity() ,View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mBaseIntent:Intent
    lateinit var mJobIntent:Intent
    lateinit var mNotiIntent:Intent
    lateinit var mOneIntent:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this,R.layout.activity_main) as ActivityMainBinding

        binding.btnBaseStart.setOnClickListener(this)
        binding.btnBaseStop.setOnClickListener(this)

        binding.btnBaseStartjob.setOnClickListener(this)
        binding.btnBaseStopjob.setOnClickListener(this)

        binding.btnBaseStartnoti.setOnClickListener(this)
        binding.btnBaseStopnoti.setOnClickListener(this)

        binding.btnBaseStart1px.setOnClickListener(this)
        binding.btnBaseStop1px.setOnClickListener(this)

        binding.btnBaseStartone.setOnClickListener(this)
        binding.btnBaseStopone.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_base_start -> {
                mBaseIntent = Intent(this, BaseService::class.java)
                startService(mBaseIntent)
            }
            R.id.btn_base_stop -> {
                stopService(mBaseIntent)
            }

            R.id.btn_base_startjob -> {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    mJobIntent = Intent(this, MyJobSheduler::class.java)
                    startService(mJobIntent)
                } else {
                    Toast.makeText(this,"SDK want to 19",Toast.LENGTH_SHORT).show()
                }

            }
            R.id.btn_base_stopjob -> {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    stopService(mJobIntent)
                } else {
                    Toast.makeText(this,"SDK want to 19",Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_base_startnoti -> {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    mNotiIntent = Intent(this, NotificationService::class.java)
                    startService(mNotiIntent)
                } else {
                    Toast.makeText(this,"SDK want to 19",Toast.LENGTH_SHORT).show()
                }

            }
            R.id.btn_base_stopnoti-> {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    stopService(mNotiIntent)
                } else {
                    Toast.makeText(this,"SDK want to 19",Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_base_start1px -> {
                LiveActivity.register(this)
            }
            R.id.btn_base_stop1px-> {
                LiveActivity.release()
            }

            R.id.btn_base_startone -> {
                mOneIntent = Intent(this, ForeService::class.java)
                startService(mOneIntent)
            }
            R.id.btn_base_stopone-> {
                stopService(mOneIntent)
            }
        }
    }

}
