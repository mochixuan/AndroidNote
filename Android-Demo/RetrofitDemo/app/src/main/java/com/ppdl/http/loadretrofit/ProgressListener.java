package com.ppdl.http.loadretrofit;


public interface ProgressListener {

    /**
     * @param progress 已下载字节数
     * @param total     总字节数
     * @param done      是否完成
     */
    void onProgress(long progress, long total, boolean done);

}
