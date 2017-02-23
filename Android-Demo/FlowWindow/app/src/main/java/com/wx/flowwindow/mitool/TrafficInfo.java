package com.wx.flowwindow.mitool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;

//应用的流量信息
public class TrafficInfo {

    private final int UNSUPPORTED = -1;
    private int uid;
    private long preRxBytes = 0;

    private static volatile TrafficInfo INSTANCE;

    public static TrafficInfo getInstance() {
        if (INSTANCE==null) {
            synchronized (TrafficInfo.class) {
                if (INSTANCE==null) {
                    INSTANCE = new TrafficInfo();
                }
            }
        }
        return INSTANCE;
    }

    //获取总流量
    public long getTrafficInfo() {
        long rcvTraffic = UNSUPPORTED;          // 下载流量
        long sndTraffic = UNSUPPORTED;          // 上传流量
        rcvTraffic = getRcvTraffic();
        sndTraffic = getSndTraffic();
        if (rcvTraffic == UNSUPPORTED || sndTraffic == UNSUPPORTED)
            return UNSUPPORTED;
        else
            return rcvTraffic + sndTraffic;
    }

    //获取下载流量 某个应用的网络流量数据保存在系统的/proc/uid_stat/$UID/tcp_rcv | tcp_snd文件中
    private long getRcvTraffic() {
        long rcvTraffic = UNSUPPORTED; // 下载流量
        rcvTraffic = TrafficStats.getUidRxBytes(uid);
        if (rcvTraffic == UNSUPPORTED) { // 不支持的查询
            return UNSUPPORTED;
        }

        RandomAccessFile rafRcv = null, rafSnd = null; // 用于访问数据记录文件
        String rcvPath = "/proc/uid_stat/" + uid + "/tcp_rcv";
        try {
            rafRcv = new RandomAccessFile(rcvPath, "r");
            rcvTraffic = Long.parseLong(rafRcv.readLine()); // 读取流量统计
        } catch (FileNotFoundException e) {
            rcvTraffic = UNSUPPORTED;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rafRcv != null)
                    rafRcv.close();
                if (rafSnd != null)
                    rafSnd.close();
            } catch (IOException e) {
            }
        }
        return rcvTraffic;
    }

    //获取上传流量
    private long getSndTraffic() {
        long sndTraffic = UNSUPPORTED;                  // 上传流量
        sndTraffic = TrafficStats.getUidTxBytes(uid);
        if (sndTraffic == UNSUPPORTED) {                // 不支持的查询
            return UNSUPPORTED;
        }

        RandomAccessFile rafRcv = null, rafSnd = null; // 用于访问数据记录文件
        String sndPath = "/proc/uid_stat/" + uid + "/tcp_snd";
        try {
            rafSnd = new RandomAccessFile(sndPath, "r");
            sndTraffic = Long.parseLong(rafSnd.readLine());
        } catch (FileNotFoundException e) {
            sndTraffic = UNSUPPORTED;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rafRcv != null)
                    rafRcv.close();
                if (rafSnd != null)
                    rafSnd.close();
            } catch (IOException e) {
            }
        }
        return sndTraffic;
    }

    //获取当前下载流量总和
    private long getNetworkRxBytes() {
        return TrafficStats.getTotalRxBytes();
    }

    //获取当前上传流量总和
    public long getNetworkTxBytes() {
        return TrafficStats.getTotalTxBytes();
    }

    //获取当前网速 kb/s
    private double getNetSpeed() {
        long curRxBytes = getNetworkRxBytes();
        if (preRxBytes == 0)
            preRxBytes = curRxBytes;
        long bytes = curRxBytes - preRxBytes;
        preRxBytes = curRxBytes;
        double kb = (double) bytes / (double) 1024;
        BigDecimal bd = new BigDecimal(kb);
        return bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //获取当前网速带单位
    public String getNetSpeed2String() {
        long curRxBytes = getNetworkRxBytes();
        if (preRxBytes == 0)
            preRxBytes = curRxBytes;
        long bytes = curRxBytes - preRxBytes;
        preRxBytes = curRxBytes;
        String postfix = "b/s" ;
        double showTrafficNumber = 0d ;
        if(bytes < 1000){
            showTrafficNumber = (double) bytes ;
        }else if(bytes >= 1000 && bytes < 1000 * 1000){
            showTrafficNumber = (double) bytes / 1024 ;
            postfix = "k/s" ;
        }else if(bytes >= 1000 * 1000){
            showTrafficNumber = (double) bytes / ( 1024 * 1024) ;
            postfix = "M/s" ;
        }
        BigDecimal bd = new BigDecimal(showTrafficNumber);
        return bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + postfix ;   //)四舍五入，2.35变成2.4 百度去
    }

    public String getOnceAllBytes(Context context) {
        long rxTraffic = TrafficStats.getUidRxBytes(uid);
        long txTraffic = TrafficStats.getUidTxBytes(uid);
        double count = rxTraffic+txTraffic;
        if (count<=0) {
            return count+"";
        }
        if (count<1024) {
            return count+"B";
        } else if (count<1024*1024) {
            return count/1024+"kb";
        } else if (count<1024*1024*1024){
            return count/1024/1024+"M";
        }
        return "-1";
    }

    public void initUid(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            uid = ai.uid;
        }catch (Exception e) {

        }
    }


}
