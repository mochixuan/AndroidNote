package com.wx.dialogmaster.activity

import android.app.Instrumentation
import android.databinding.ViewDataBinding
import android.view.KeyEvent
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityKeyControllBinding
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class KeyControllActivity : BaseActivity() {

    private lateinit var binding: ActivityKeyControllBinding

    override val layoutId: Int
        get() = R.layout.activity_key_controll

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityKeyControllBinding
    }

    override fun initData() {

        val mInstrumentation = Instrumentation()
        //mInstrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
        binding.btn1.setOnClickListener {
            Thread(Runnable {
                Runtime.getRuntime().exec("input keyevent "+83)
                //mInstrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
                //mInstrumentation.sendCharacterSync(KeyEvent.KEYCODE_DPAD_LEFT)
            }).start()

        }

        Observable.interval(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    System.out.println("=====================================>>>>>")
                    //mInstrumentation.sendCharacterSync(KeyEvent.KEYCODE_DPAD_LEFT)
                    Runtime.getRuntime().exec("input keyevent "+83)
                })

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        binding.tvDetail.setText("down: "+event!!.action.toString()+"  "+event.keyCode)
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        binding.tvDetail1.setText("up: "+event!!.action.toString()+"  "+event.keyCode)
        return super.onKeyUp(keyCode, event)
    }

}
