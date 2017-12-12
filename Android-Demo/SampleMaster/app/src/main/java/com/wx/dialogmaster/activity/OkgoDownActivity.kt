package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import com.lzy.okgo.OkGo
import com.lzy.okgo.db.DownloadManager
import com.lzy.okgo.model.Progress
import com.lzy.okserver.OkDownload
import com.lzy.okserver.download.DownloadListener
import com.wx.dialogmaster.R
import com.wx.dialogmaster.adapter.FileDownAdapter
import com.wx.dialogmaster.base.BaseActivity
import com.wx.dialogmaster.constant.DataConstant
import com.wx.dialogmaster.databinding.ActivityRetrofit2Binding
import com.wx.dialogmaster.model.FileBaseDownModel
import com.wx.dialogmaster.model.FileBaseUiModel
import com.wx.dialogmaster.model.FileDownListener
import com.wx.dialogmaster.model.FileModel
import com.wx.dialogmaster.widget.RecycleViewDivider
import java.io.File

class OkgoDownActivity : BaseActivity() {

    private lateinit var binding: ActivityRetrofit2Binding;
    private lateinit var mAdapter: FileDownAdapter
    private val mFileDatas = ArrayList<FileModel>();

    override val layoutId: Int
        get() = R.layout.activity_retrofit2

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityRetrofit2Binding
    }

    override fun initData() {

        //从数据库中恢复数据
        OkDownload.restore(DownloadManager.getInstance().all)

        DataConstant.FileTitles.forEachIndexed { index,item ->

            var progress = 0;
            var btnState = "下载"
            if (OkDownload.getInstance().hasTask(DataConstant.FileUrls[index])) {
                progress = (OkDownload.getInstance().getTask(DataConstant.FileUrls[index]).progress.fraction*100).toInt()
                when (OkDownload.getInstance().getTask(DataConstant.FileUrls[index]).progress.status) {
                    Progress.NONE -> btnState = "下载"
                    Progress.PAUSE -> btnState = "继续"
                    Progress.ERROR -> btnState = "出错"
                    Progress.WAITING -> btnState = "等待"
                    Progress.FINISH -> btnState = "完成"
                    Progress.LOADING -> btnState = "暂停"
                }
            }
            var downModel = FileBaseDownModel(DataConstant.FileUrls[index],DataConstant.FileImages[index],"0M",DataConstant.FileTotalSizes[index].toString())
            var uiModel = FileBaseUiModel(btnState,DataConstant.FileTitles[index],DataConstant.FileDescris[index],progress);
            mFileDatas.add(FileModel(downModel,uiModel))
        }



        val layoutManager = LinearLayoutManager(this)
        mAdapter = FileDownAdapter(this)
        mAdapter.setData(mFileDatas,false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,R.mipmap.div_line))
        binding.recyclerView.adapter = mAdapter

        mAdapter.setFileDownListener(object : FileDownListener{
            override fun clickBtn(index: Int) {
                val fileModel = mFileDatas.get(index);
                val request = OkGo.get<File>(fileModel.downModel.fileUrl)
                OkDownload.request(fileModel.downModel.fileUrl,request)
                        .fileName(fileModel.uiModel.title+".apk")
                        .register(DesListener(fileModel.downModel.fileUrl,index))
                        .save()
                        .start()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        val taskMaps = OkDownload.getInstance().taskMap
        taskMaps.forEach {
            it.value.unRegister(it.value.progress.tag)
        }
    }

    private inner class DesListener constructor(tag: String): DownloadListener(tag) {

        private var index: Int = 0

        constructor(tag: String,index: Int) : this(tag) {
            this.index = index
        }

        override fun onFinish(t: File?, progress: Progress?) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onFinish")
            refresh(index,"下载完成",progress!!)
        }

        override fun onRemove(progress: Progress?) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onRemove")
        }

        override fun onProgress(progress: Progress?) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onProgress")
            if ( mFileDatas.get(index).uiModel.progress !== (progress!!.fraction*100).toInt()) {
                refresh(index,"下载中",progress!!)
            }
        }

        override fun onError(progress: Progress?) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onError")
            refresh(index,"下载失败",progress!!)
        }

        override fun onStart(progress: Progress?) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onStart")
            refresh(index,"下载中",progress!!)
        }
    }

    private fun refresh(index: Int,btnState: String,progress: Progress) {
        mFileDatas.get(index).uiModel.btnState = btnState
        mFileDatas.get(index).uiModel.progress = (progress.fraction*100).toInt()
        mAdapter.notifyItemChanged(index)
    }


}
