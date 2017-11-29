package com.wx.crashlibrary.model;

import android.Manifest;
import android.support.annotation.RequiresPermission;

public class DeviceInfo {

    //获取设备宽度
    private String screenWidth;

    //获取设备高度
    private String screenHeight;

    //获取屏幕密度
    private String densityDpi;

    //应用名
    private String appName;

    //应用包名
    private String packName;

    //应用版本
    private String versionName;

    //应用版本号
    private String versionCode;

    //获取系统语言
    private String systemLanguage;

    //获取系统版本号
    private String systemVersion;

    //获取手机厂商号
    private String deviceBrand;

    //获取手机IMEI(移动用户标志)
    private String imei;

    //获取手机型号
    private String systemModel;

    //获取设备ID
    private String deviceId;

    //获取手机号
    private String phoneNum;

    //获取SIM卡号
    private String simCode;

    //获取网络状态
    private String apnType;

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(String densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getSystemLanguage() {
        return systemLanguage;
    }

    public void setSystemLanguage(String systemLanguage) {
        this.systemLanguage = systemLanguage;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @RequiresPermission(allOf = {Manifest.permission.READ_PHONE_STATE})
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public String getApnType() {
        return apnType;
    }

    public void setApnType(String apnType) {
        this.apnType = apnType;
    }
}
