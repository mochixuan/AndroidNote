package com.wx.crashlibrary.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.wx.crashlibrary.model.DeviceInfo;

import java.util.Locale;

public class DeviceInfoManager {

    @RequiresPermission(allOf = {Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_NETWORK_STATE})
    public static Builder builder(Context context){
        return new Builder(context);
    }

    public static class Builder{

        private DeviceInfo mDeviceInfo;
        private final String NOTNULL = "Not Get";

        public Builder(Context context) {
            initDeviceInfo(context);
        }

        public String getJsonData() {
            if (mDeviceInfo != null) {
                try {
                    Gson gson = new Gson();
                    return gson.toJson(mDeviceInfo);
                } catch (Exception e) {}
            }
            return NOTNULL;
        }

        public DeviceInfo getDeviceInfoData() {
            return mDeviceInfo;
        }

        private void initDeviceInfo(Context context) {
            if (mDeviceInfo == null) {
                mDeviceInfo = new DeviceInfo();
            }
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();

            DisplayMetrics dm = new DisplayMetrics();
            dm = context.getResources().getDisplayMetrics();
            String screenWidth = dm.widthPixels+"";
            String screenHeight = dm.heightPixels+"";
            String densityDpi = dm.densityDpi+"";
            String appName = (String) packageManager.getApplicationLabel(applicationInfo);
            String packName = context.getPackageName();
            String versionName;
            String versionCode;
            try {
                versionName = packageManager.getPackageInfo(packName,0).versionName;
                versionCode = packageManager.getPackageInfo(packName, 0).versionCode+"";
            } catch (PackageManager.NameNotFoundException e) {
                versionName = NOTNULL;
                versionCode = NOTNULL;
            }
            String systemLanguage = Locale.getDefault().getLanguage();
            String systemVersion = Build.VERSION.RELEASE;
            String deviceBrand = Build.BRAND;
            String systemModel = Build.MODEL;
            String imei;
            String deviceId;
            String simCode;
            String phoneNum;
            TelephonyManager tele = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
            if (tele != null) {
                imei = tele.getSubscriberId();
                deviceId = tele.getDeviceId();
                simCode = tele.getSimSerialNumber();
                phoneNum = tele.getLine1Number();           //老sim好像才行
            } else {
                imei = NOTNULL;
                deviceId = NOTNULL;
                simCode = NOTNULL;
                phoneNum = NOTNULL;
            }
            String apnType = getAPNType(context);

            mDeviceInfo.setScreenWidth(checkData(screenWidth));
            mDeviceInfo.setScreenHeight(checkData(screenHeight));
            mDeviceInfo.setDensityDpi(checkData(densityDpi));
            mDeviceInfo.setAppName(checkData(appName));
            mDeviceInfo.setPackName(checkData(packName));
            mDeviceInfo.setVersionName(checkData(versionName));
            mDeviceInfo.setVersionCode(checkData(versionCode));
            mDeviceInfo.setSystemLanguage(checkData(systemLanguage));
            mDeviceInfo.setSystemVersion(checkData(systemVersion));
            mDeviceInfo.setDeviceBrand(checkData(deviceBrand));
            mDeviceInfo.setImei(checkData(imei));
            mDeviceInfo.setSystemModel(checkData(systemModel));
            mDeviceInfo.setDeviceId(checkData(deviceId));
            mDeviceInfo.setPhoneNum(checkData(phoneNum));
            mDeviceInfo.setSimCode(checkData(simCode));
            mDeviceInfo.setApnType(checkData(apnType));

        }

        //防止数据为空
        private String checkData(String data) {
            if (null != data){
                return data;
            } else {
                return NOTNULL;
            }
        }

        private String getAPNType(Context context) {
            String netType = "No_Connect";
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return netType;
            }
            int nType = networkInfo.getType();
            if (nType == ConnectivityManager.TYPE_WIFI) {
                netType = "Wifi";
            } else if (nType == ConnectivityManager.TYPE_MOBILE) {
                int nSubType = networkInfo.getSubtype();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
                        && !telephonyManager.isNetworkRoaming()) {
                    netType = "4G";
                } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !telephonyManager.isNetworkRoaming()) {
                    netType = "3G";
                } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS || nSubType == TelephonyManager.NETWORK_TYPE_EDGE || nSubType == TelephonyManager.NETWORK_TYPE_CDMA && !telephonyManager.isNetworkRoaming()) {
                    netType = "2G";
                } else {
                    netType = "2G";
                }
            }
            return netType;
        }

    }

}
