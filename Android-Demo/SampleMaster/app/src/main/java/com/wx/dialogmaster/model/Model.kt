package com.wx.dialogmaster.model

data class FileBaseDownModel(var fileUrl: String ,var fileImg: String , var fileCurSize: String,var fileTotleSize: String)
data class FileBaseUiModel(var btnState: String ,var title: String,var descri: String,var progress: Int , var fileListener: FileDownListener)
data class FileModel(var downModel: FileBaseDownModel,var uiModel: FileBaseUiModel)

interface FileDownListener {
    fun clickBtn(index: Int)
}