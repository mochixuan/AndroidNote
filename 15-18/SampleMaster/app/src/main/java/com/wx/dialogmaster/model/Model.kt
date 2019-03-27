package com.wx.dialogmaster.model

data class FileBaseDownModel(var fileUrl: String ,var fileImg: String , var fileCurSize: String,var fileTotleSize: String)
data class FileBaseUiModel(var btnState: String ,var title: String,var descri: String,var progress: Int )
data class FileModel(var downModel: FileBaseDownModel,var uiModel: FileBaseUiModel)
data class AmapModel(var poiid: String,var distance: Int,var poitype: Int,var address: String,var tel: String,var name: String,var longitude: Double,var latitude: Double)


interface FileDownListener {
    fun clickBtn(index: Int)
}

