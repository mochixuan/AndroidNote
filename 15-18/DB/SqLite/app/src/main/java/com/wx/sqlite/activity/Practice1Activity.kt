package com.wx.sqlite.activity

import android.databinding.ViewDataBinding
import android.text.method.ScrollingMovementMethod
import com.wx.glidedemo1.base.BaseActivity
import com.wx.sqlite.R
import com.wx.sqlite.databinding.ActivityPractice1Binding
import com.wx.sqlite.db.DBConstant
import com.wx.sqlite.db.DBManager
import com.wx.sqlite.db.Person

class Practice1Activity : BaseActivity() {

    private lateinit var binding: ActivityPractice1Binding
    private var mPersonId = 0

    override val layoutId: Int
        get() = R.layout.activity_practice1

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityPractice1Binding
    }

    override fun initData() {
        binding.tvDetail1.movementMethod = ScrollingMovementMethod.getInstance()
        binding.insert.setOnClickListener {
            val person = Person(mPersonId,"mochixuan"+mPersonId,20+mPersonId)
            val result = DBManager.getInstance(Practice1Activity@this).insertPerson(person)
            setTipMsg("成功插入: ${result} 条数据")
            mPersonId = mPersonId.inc()
        }
        binding.delete.setOnClickListener {
            val person = Person(0,"mochixuan",23)
            val result = DBManager.getInstance(Practice1Activity@this).deletePerson(person)
            setTipMsg("成功删除: ${result} 条数据")
        }
        binding.update.setOnClickListener {
            val person = Person(0,"mochixuan",23)
            DBManager.getInstance(Practice1Activity@this).updatePerson(person)
            setTipMsg("修改成功: "+0)
        }
        binding.select.setOnClickListener {
            val person = Person(0,"mochixuan",23)
            val result = DBManager.getInstance(Practice1Activity@this).queryPerson(person)
            var tip = StringBuffer()
            result.forEach {
                tip.append("${DBConstant._ID}: ${it.id}  ${DBConstant.NAME}: ${it.name}  ${DBConstant.AGE}: ${it.age} \n")
            }
            binding.tvDetail1.text = tip.toString()
        }
        binding.selectAll.setOnClickListener {
            val result = DBManager.getInstance(Practice1Activity@this).queryAllPerson()
            var tip = StringBuffer()
            result.forEach {
                tip.append("${DBConstant._ID}: ${it.id}  ${DBConstant.NAME}: ${it.name}  ${DBConstant.AGE}: ${it.age} \n")
            }
            binding.tvDetail1.text = tip.toString()
        }
        binding.deleteAll.setOnClickListener {
            val result = DBManager.getInstance(Practice1Activity@this).deleteAllPerson()
            setTipMsg("删除所有共: ${result} 条数据")
        }
    }

    fun setTipMsg(tip: String) {
        binding.tvDetail.text = tip
    }

}
