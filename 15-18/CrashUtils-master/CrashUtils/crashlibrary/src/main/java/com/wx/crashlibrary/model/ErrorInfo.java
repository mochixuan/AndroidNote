package com.wx.crashlibrary.model;

public class ErrorInfo {

    private String errorlog;

    private String errorTime;

    private DeviceInfo deviceInfo;

    public ErrorInfo() {

    }

    public ErrorInfo(String errorlog, String errorTime, DeviceInfo deviceInfo) {
        this.errorlog = errorlog;
        this.errorTime = errorTime;
        this.deviceInfo = deviceInfo;
    }

    public String getErrorlog() {
        return errorlog;
    }

    public void setErrorlog(String errorlog) {
        this.errorlog = errorlog;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
