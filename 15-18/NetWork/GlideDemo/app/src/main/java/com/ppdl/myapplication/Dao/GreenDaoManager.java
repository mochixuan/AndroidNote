package com.ppdl.myapplication.Dao;

import com.ppdl.myapplication.Application.MyApplication;

import java.util.List;

public class GreenDaoManager {

    private static volatile GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;

    private GreenDaoManager(){
        devOpenHelper=new DaoMaster.DevOpenHelper(
                MyApplication.getContext(),"message",null);
        mDaoMaster=new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession=mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance(){
        if(mInstance==null){
            synchronized (GreenDaoManager.class){
                if (mInstance==null)
                    mInstance=new GreenDaoManager();
            }
        }
        return mInstance;
    }

    public boolean insert(Long id,String json){
        if(json !=null && !json.isEmpty()){
            dataBean bean=new dataBean();
            bean.setJson(json);
            mDaoSession.insert(bean);
            return true;
        }
        return false;
    }

    public void delete(dataBean dataBean){
        mDaoSession.delete(dataBean);
    }

    public void updata(dataBean bean){
        mDaoSession.update(bean);
    }

    public dataBean query(long id){
        dataBean bean=mDaoSession.load(dataBean.class,id);
        return bean;
    }

    public List<dataBean> queryAll(){
        List<dataBean> mBeens=mDaoSession.loadAll(dataBean.class);
        return mBeens;
    }

    public void close(){
        if(mDaoSession!=null){
            mDaoSession.clear();
        }
        if(devOpenHelper!=null){
            devOpenHelper.close();
        }
    }

}
