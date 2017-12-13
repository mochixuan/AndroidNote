package com.wx.dialogmaster.activity

import android.app.Activity
import android.content.Intent
import android.databinding.ViewDataBinding
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.databinding.ActivityAndroid7Binding
import java.io.File

class Android7Activity : BaseActivity() {

    private lateinit var binding: ActivityAndroid7Binding
    private var mImageFile: File? = null

    override val layoutId: Int
        get() = R.layout.activity_android7

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityAndroid7Binding
    }

    override fun initData() {

        binding.btn1.setOnClickListener {
            //根据路径实例化图片文件
            mImageFile = File(Environment.getExternalStorageDirectory(),"/temp/"+System.currentTimeMillis()+".jpg")
            if (!mImageFile!!.parentFile.exists()) {
                mImageFile!!.parentFile.mkdirs()
            }

            var imageUri = Uri.fromFile(mImageFile)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                imageUri = FileProvider.getUriForFile(this,"com.wx.dialogmaster.fileProvider",mImageFile!!)
            }

            //实例化intent,指向摄像头
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //设置拍照后图片保存到文件中
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            //启动拍照activity并获取返回数据
            startActivityForResult(intent,110)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 110 && resultCode == Activity.RESULT_OK) {
            if (mImageFile != null && mImageFile!!.exists()) {
                binding.ivImg.setImageBitmap(BitmapFactory.decodeFile(mImageFile!!.path))
            }
        }

    }

}
