package com.wx.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by wangxuan on 2018/2/3.
 */


class DBOpenHelper(val context: Context,val  name: String,val  factory: SQLiteDatabase.CursorFactory?,val version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    private var mTableName: String? = null

    /**
     * context:  上下文对象
     * name:     表示创建数据库的名称
     * factory:  游标工厂
     * version:  版本号
     */
    constructor(context: Context) : this(context,DBConstant.DB_NAME,null, DBConstant.VERSION)

    /**
     * 创建数据库时
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table ${DBConstant.TABLE_PERSON}(${DBConstant._ID} Integer primary key,${DBConstant.NAME} varchar(10),${DBConstant.AGE} Integer)"
        db?.execSQL(sql)
    }

    //升级库
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //打开数据库
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)

    }

}

