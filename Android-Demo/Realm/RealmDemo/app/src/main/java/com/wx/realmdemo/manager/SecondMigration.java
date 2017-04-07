package com.wx.realmdemo.manager;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class SecondMigration implements RealmMigration{

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        /** v0 - v1
         * 删除User库中isMarriage(boolean)属性
         * 为User库添加isMale属性
         * */
        if (oldVersion==0) {
            schema.get("User")
                    .addField("isMale",boolean.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("isMale",false);    //已经有的数据中isMale默认为false
                        }
                    })
                    .removeField("isMarriage");
        }


    }

}
