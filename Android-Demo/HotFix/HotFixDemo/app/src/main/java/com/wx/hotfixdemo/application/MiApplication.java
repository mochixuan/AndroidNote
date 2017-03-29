package com.wx.hotfixdemo.application;

import android.app.Application;

import com.taobao.android.SophixManager;
import com.taobao.android.listener.PatchLoadStatusListener;
import com.taobao.android.util.PatchStatus;
import com.wx.hotfixdemo.tool.VersionTool;

public class MiApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initHotFix();
    }

    private void initHotFix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion(VersionTool.getVersionName(getApplicationContext()))
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onload(int mode, int code, String info, int handlePatchVersion) {
                        System.out.println("=================================================mode>>"+mode);
                        System.out.println("=================================================code>>"+code);
                        System.out.println("=================================================info>>"+info);
                        System.out.println("=================================================handlePatchVersion>>"+handlePatchVersion);
                        switch (code) {
                            case PatchStatus.CODE_LOAD_SUCCESS:           //表明补丁加载成功
                                break;
                            case PatchStatus.CODE_LOAD_RELAUNCH:          //表明新补丁生效需要重启.开发者可提示用户或者强制重启; 建议:用户可以监听进入后台事件, 然后应用自杀
                                break;
                            case PatchStatus.CODE_LOAD_FAIL:              //内部引擎异常,推荐此时清空本地补丁,防止失败补丁重复加载 SophixManager.getInstance().cleanPatches();
                                break;
                            default:                                      //其它错误信息,查看PatchStatus类说明
                                break;
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();             //获取更新信息并且更新
    }



}
