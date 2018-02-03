package com.wx.sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by wangxuan on 2018/2/3.
 */

class DBManager {

    companion object {
        private var INSTANCE: DBManager? = null

        fun getInstance(context: Context): DBManager {
            if (INSTANCE == null) {
                synchronized(DBManager::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DBManager(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    private var mHelper: DBOpenHelper? = null
    private var mDB: SQLiteDatabase? = null

    private constructor(context: Context) {
        mHelper = DBOpenHelper(context)
        /**
         * 创建或者打开数据库
         * 如果数据库不存在则创建数据，如果存在则直接打开数据库
         */
    }

    //增
    fun insertPerson(person: Person): Long{
        openDB()
        //顺序不能错 方法一
        /*val sql = "insert into ${DBConstant.TABLE_PERSON} values(${person.id},'${person.name}',${person.age})"
        mDB?.execSQL(sql)*/
        /**
         * 方法二
         * table表面
         * nullColumnHack
         * contetValues : 键为String类型的hashMap集合
         * 返回 long表示数据列数没插入前的数目
         */
        val values = ContentValues()
        values.put(DBConstant._ID,person.id)
        values.put(DBConstant.NAME,person.name)
        values.put(DBConstant.AGE,person.age)
        val result = mDB?.insert(DBConstant.TABLE_PERSON,null,values)
        closeDB()
        return result!!
    }

    //删
    fun deletePerson(person: Person): Int {
        openDB()
        /*var filter = StringBuilder()
        var isHasAppend = false
        if (person.id != -1) {
            filter.append(DBConstant._ID+"="+person.id)
            isHasAppend = true
        }
        if (person.name != null && person.name.length != 0) {
            if (isHasAppend) filter.append(" and ")
            filter.append(DBConstant.NAME+"="+"'"+person.name+"'")
        }
        if (person.age != -1) {
            if (isHasAppend) filter.append(" and ")
            filter.append(DBConstant.AGE+"="+person.age)
        }
        val sql = "delete from ${DBConstant.TABLE_PERSON} where ${filter.toString()}"
        mDB?.execSQL(sql)*/
        val result = mDB?.delete(DBConstant.TABLE_PERSON,"${DBConstant._ID}=${person.id}",null)
        closeDB()
        return result!!
    }

    fun deleteAllPerson(): Int {
        openDB()
        /*val sql = "delete from ${DBConstant.TABLE_PERSON}"
        mDB?.execSQL(sql)*/
        /**
         * table 表名
         * whereClause 修改条件
         * string[] 修改条件占位符
         * count 返回被修改的数目
         */
        val result = mDB?.delete(DBConstant.TABLE_PERSON,null,null)
        closeDB()
        return result!!
    }

    //改
    fun updatePerson(person: Person): Int {
        openDB()
        /*val sql = "update ${DBConstant.TABLE_PERSON} set name='${person.name}',age=${person.age} where ${DBConstant._ID}=${person.id}"
        mDB?.execSQL(sql)*/
        /**
         * table 表名
         * ContentValues 表示键为String类型的hashMap
         * whereClause 修改条件
         * string[] 修改条件占位符
         * count 返回被修改的数目
         */
        val values = ContentValues()
        values.put(DBConstant._ID,person.id)
        values.put(DBConstant.NAME,person.name)
        values.put(DBConstant.AGE,person.age)
        //val result = mDB?.update(DBConstant.TABLE_PERSON,values,"${DBConstant._ID}=${person.id}",null)
        val result = mDB?.update(DBConstant.TABLE_PERSON,values,"${DBConstant._ID}=?", arrayOf(person.id.toString()))
        closeDB()
        return result!!
    }

    //查
    fun queryPerson(person: Person): MutableList<Person> {
        val persons = mutableListOf<Person>()
        openDB()
        val sql = "select * from ${DBConstant.TABLE_PERSON} where age>=25 and age<=30 order by ${DBConstant.AGE} DESC"
        val cursor = mDB?.rawQuery(sql,null)
        while (cursor!!.moveToNext()) {
            val person = Person(
                    cursor.getInt(cursor.getColumnIndex(DBConstant._ID)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.NAME)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.AGE))
            )
            persons.add(person)
        }
        /**
         * table String 表面
         * columns String[] 查询字段名称 null表示所以
         * selection String[] 表示查询语句 where句子
         * selectArgs String[] 表示查询占位符取值
         * groupBy String group by 分组
         * having String 筛选条件
         * orderBy String 表示排序条件 如"_id desc"则倒叙不加"_id"正序
         */
        //mDB?.query()
        closeDB()
        return persons
    }
    fun queryAllPerson(): MutableList<Person> {
        val persons = mutableListOf<Person>()
        openDB()
        val sql = "select * from ${DBConstant.TABLE_PERSON}"
        val cursor = mDB?.rawQuery(sql,null)
        while (cursor!!.moveToNext()) {
            val person = Person(
                    cursor.getInt(cursor.getColumnIndex(DBConstant._ID)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.NAME)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.AGE))
            )
            persons.add(person)
        }
        closeDB()
        return persons
    }

    fun openDB() {
        if (mDB == null || !mDB!!.isOpen) {
            mDB = mHelper?.writableDatabase
        }
    }

    fun closeDB() {
        if (mDB != null && mDB!!.isOpen) {
            mDB?.close()
        }
    }

}