package com.wx.realmdemo.manager;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {

    private static RealmManager INSTANCE;
    private Realm mRealm;

    public static RealmManager getInstance() {
        if (INSTANCE==null) {
            synchronized (RealmManager.class) {
                if (INSTANCE==null)
                    INSTANCE = new RealmManager();
            }
        }
        return INSTANCE;
    }

    public void  init(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("wxrealm.realm")
                .schemaVersion(1)
                .migration(new SecondMigration())
                .build();
        mRealm = Realm.getInstance(config);
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void release() {
        if (mRealm != null)
            mRealm.close();
    }

}
