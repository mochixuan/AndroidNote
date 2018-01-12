package com.wx.dialogmaster.fragment

import android.databinding.DataBindingUtil
import android.os.*
import android.support.v7.app.AppCompatActivity
import com.wx.dialogmaster.R
import com.wx.dialogmaster.databinding.ActivityFragmentTestBinding
import com.wx.dialogmaster.fragment.fragment.Fragment1
import com.wx.dialogmaster.fragment.fragment.Fragment2
import com.wx.dialogmaster.fragment.fragment.Fragment3

class FragmentTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_fragment_test)

        //横竖屏切换时注意
        if (savedInstanceState != null) {
            val fragment11 = Fragment1()
            val fragment12 = Fragment2()
            val fragment13 = Fragment3()

            val manager = supportFragmentManager
            manager.beginTransaction().replace(R.id.frame_layout,fragment11,"f11").addToBackStack(null).commit()
            manager.beginTransaction().replace(R.id.frame_layout,fragment12,"f12").addToBackStack(null).commit()
            manager.beginTransaction().replace(R.id.frame_layout,fragment13,"f13").addToBackStack(null).commit()
        }

        val message = Message()


    }

    inner class DownloadTask : AsyncTask<Void,Int,Boolean>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Boolean {
            onProgressUpdate(10)
            return false
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
        }

        override fun onCancelled(result: Boolean?) {
            super.onCancelled(result)

        }

    }

    val handlerThread = HandlerThread("myHandlerThread")
    val mHandler = object : Handler(handlerThread.looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

        }
    }

    fun test() {
        DownloadTask().execute()
    }

}
