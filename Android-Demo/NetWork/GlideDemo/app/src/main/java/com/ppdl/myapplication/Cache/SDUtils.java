package com.ppdl.myapplication.Cache;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class SDUtils {

    public static float maxMemory(){
        //设置缓存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int memoryCacheSize=maxMemory/8;
        return maxMemory/1024/1024;
    }

    // 判断SD卡是否被挂载
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    // 获得手机内部存储控件的状态
    public static String getMemoryInfo() {

        if(!Environment.getDataDirectory().exists())
            return "";

        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = stat.getBlockSize();                                           // 获得一个扇区的大小
        long totalBlocks = stat.getBlockCount();                                        // 获得扇区的总数
        long availableBlocks = stat.getAvailableBlocks();                               // 获得可用的扇区数量
        long totalMemory =  totalBlocks * blockSize/1024/1024;                          // 总空间
        long availableMemory =availableBlocks * blockSize/1024/1024;                    // 可用空间
        return "内部SD总空间: " + totalMemory + "\n可用空间: " + availableMemory;
    }

    // 获得手机内部存储控件的状态
    public static int getFreeEXternalSize(String path) {

        if(path == null)
            return 0;

        File file = new File(path);
        if(!file.exists())
            return 0;

        StatFs stat = new StatFs(file.getPath());                                       // 获得一个磁盘状态对象
        //long totalBlocks = stat.getBlockCount();                                      // 获得扇区的总数
        //long totalMemory =  totalBlocks * blockSize/1024/1024;                        // 总空间
        long blockSize = stat.getBlockSize();                                           // 获得一个扇区的大小
        long availableBlocks = stat.getAvailableBlocks();                               // 获得可用的扇区数量
        int availableMemory = (int) (availableBlocks * blockSize/1024/1024);            // 可用空间

        return availableMemory;
    }

    //路径设置
    public static boolean setDirectory(String path){

        if(path==null) return false;
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return true;
    }

    public static void deleteDirectory(String path){
        if(path==null)  return;
        final File file = new File(path);
        if (!file.exists()) return ;
        deletFile(file);
    }

    private static void deletFile(File file){
        if(file.isFile()){
            file.delete();
        }else if(file.isDirectory()){
            for (File fl : file.listFiles()) {
                if (fl.isFile()){
                    fl.delete();                              // 删除所有文件
                } else if (fl.isDirectory()){
                    deletFile(fl);                            // 递规的方式删除文件夹
                }
            }
            file.delete();                                    // 删除目录本身
        }
    }


}
