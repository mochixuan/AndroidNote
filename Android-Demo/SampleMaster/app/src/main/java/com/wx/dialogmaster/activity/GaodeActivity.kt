package com.wx.dialogmaster.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.databinding.ViewDataBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityGaodeBinding
import com.wx.dialogmaster.model.AmapModel

class GaodeActivity : BaseActivity() {

    private lateinit var binding: ActivityGaodeBinding

    override val layoutId: Int
        get() =  R.layout.activity_gaode

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityGaodeBinding
    }

    override fun initData() {
        this.binding.btnSearch.setOnClickListener {
            val data = binding.edSearch.text.toString()
            val intent = Intent()
            intent.action = "AUTONAVI_STANDARD_BROADCAST_RECV"
            intent.setClassName("com.autonavi.amapauto","com.autonavi.amapauto.adapter.internal.AmapAutoBroadcastReceiver")
            intent.putExtra("KEY_TYPE", 10036)
            intent.putExtra("KEYWORDS", data)
            intent.putExtra("SOURCE_APP", "com.wx.dialogmaster")
            sendBroadcast(intent)
        }
        this.binding.btnSearch1.setOnClickListener {
            val data = binding.edSearch1.text.toString()
            val intent = Intent()
            intent.action = "AUTONAVI_STANDARD_BROADCAST_RECV"
            intent.setClassName("com.autonavi.amapauto","com.autonavi.amapauto.adapter.internal.AmapAutoBroadcastReceiver")
            intent.putExtra("KEY_TYPE", 10023)
            intent.putExtra("EXTRA_KEYWORD", data)
            intent.putExtra("EXTRA_DEV", 0)
            intent.putExtra("EXTRA_SEARCHTYPE", 0)
            intent.putExtra("EXTRA_MAXCOUNT", 10)
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        registerAmapReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterAmapReceiver()
    }

    private fun registerAmapReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("AUTONAVI_STANDARD_BROADCAST_SEND")
        registerReceiver(mReceiver,intentFilter)
    }

    private fun unregisterAmapReceiver() {
        unregisterReceiver(mReceiver)
    }

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                val keyType = intent.getIntExtra("KEY_TYPE",-1)
                val result = intent.getStringExtra("EXTRA_RESULT")
                System.out.println("==============0>>"+result)
                when (keyType) {
                    10042 -> {
                        if (result != null) {
                            val gson = Gson()
                            val type =object: TypeToken<ArrayList<JsonObject>>(){}.type
                            val jsonObjects = gson.fromJson<ArrayList<JsonObject>>(result,type)
                            val arrayList = ArrayList<AmapModel>()
                            for (jsonObject in jsonObjects) {
                                arrayList.add(gson.fromJson(jsonObject,AmapModel::class.java))
                            }
                            System.out.println("==============1>>"+intent.action+" "+keyType+" "+arrayList.get(0).address)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
