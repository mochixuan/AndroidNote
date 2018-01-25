package com.ppdl.remoteclient.adapter.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ppdl.remoteclient.adapter.listener.OnItemClickListner
import com.ppdl.remoteclient.adapter.viewholder.BaseViewHolder

/**
 * Created by wangxuan on 2018/1/2.
 */

abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder>() {

    @get: LayoutRes
    abstract val layoutId: Int

    abstract fun onBindView(holder:BaseViewHolder,data: T,position: Int)

    private var mOnClickListener: OnItemClickListner? = null
    private var mDatas: MutableList<T>? = null

    init {
        mDatas = ArrayList()
    }

    fun setOnItemClickListener(listner: OnItemClickListner) {
        this.mOnClickListener = listner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),layoutId,parent,false)
        val viewHolder = BaseViewHolder(binding.root,binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindView(holder,mDatas!!.get(position),position)
        holder?.itemView?.setOnClickListener { view ->
            mOnClickListener?.onItemClick(view,position)
        }
    }

    override fun getItemCount(): Int {
        return mDatas!!.size
    }

    fun addDatas(datas: ArrayList<T>,isWantAnimator: Boolean) {
        val start = mDatas!!.size
        mDatas!!.addAll(datas)
        val end = mDatas!!.size - 1
        if (isWantAnimator)
            notifyItemRangeInserted(start,end)
        else
            notifyDataSetChanged()
    }

    fun addData(data: T,isWantAnimator: Boolean) {
        mDatas!!.add(data)
        if (isWantAnimator)
            notifyItemInserted(mDatas!!.size-1)
        else
            notifyDataSetChanged()
    }

    fun replaceAllData(datas: ArrayList<T>,isWantAnimator: Boolean) {
        mDatas!!.clear()
        mDatas!!.addAll(datas)
        if (isWantAnimator)
            notifyItemRangeInserted(0,mDatas!!.size-1)
        else
            notifyDataSetChanged()
    }

    fun getData(position: Int): T? {
        if (position >= 0 && position < mDatas!!.size) {
            return mDatas!!.get(position)
        }
        return null
    }

}

