package com.wx.uimaster.adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bigkoo.convenientbanner.ConvenientBanner
import com.wx.uimaster.R
import com.wx.uimaster.adapter.viewholder.BaseViewHolder
import com.wx.uimaster.databinding.*
import com.wx.uimaster.model.ModelThree
import com.wx.uimaster.widget.banner.MViewHolderCreator

class ComplexAdapter(private val mActivity: Activity, private val mResults: List<ModelThree>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder? {

        var binding: ViewDataBinding? = null
        when(viewType) {
            BANNER -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_banner,parent,false)
            NAV -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_nav,parent,false)
            RECOMMEND_HEAD, ACTION_HEAD, LIST_HEAD -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_head,parent,false)
            RECOMMEND -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_recommend,parent,false)
            ACTION -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_action,parent,false)
            else -> binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_list,parent,false)
        }
        val baseViewHolder: BaseViewHolder = BaseViewHolder(binding.root)
        baseViewHolder.binding = binding
        return baseViewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val binding = holder.binding

        if (binding is ItemBannerBinding) {
            val images = arrayListOf<Int>()
            mResults.get(position).icon.map { images.add(it) }
            //如果是项目将开始StartTuring和结束stopTurning放在onresume和onpause里防止内存溢出，这里就不放了
            if (!binding.banner.isTurning) {
                binding.banner.startTurning(3000)
            }
            binding.banner.setPages(MViewHolderCreator(),images)
            binding.banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
            binding.banner.setPageIndicator(intArrayOf(R.mipmap.icon_indicator_p,R.mipmap.icon_indicator_n))
        } else if (binding is ItemNavBinding) {
            binding.ivNav.setBackgroundResource(mResults[position].icon[0])
        } else if (binding is ItemHeadBinding) {
            binding.ivHead.setBackgroundResource(mResults[position].icon[0])
            binding.tvHead.setText(mResults[position].title)
        } else if (binding is ItemRecommendBinding) {
            binding.tvRecommend.setText(mResults[position].title)
            binding.ivRecommend.setBackgroundResource(mResults[position].icon[0])
        } else if (binding is ItemActionBinding) {
            binding.ivAction.setBackgroundResource(mResults[position].icon[0])
            binding.tvAction1.setText(mResults[position].title)
            binding.tvAction2.setText(mResults[position].content)
        } else if (binding is ItemListBinding) {
            binding.ivList.setBackgroundResource(mResults[position].icon[0])
            binding.tvListTitle.setText(mResults[position].title)
            binding.tvListContent.setText(mResults[position].content)
        }
    }

    override fun getItemCount(): Int {
        return mResults.size
    }

    override fun getItemViewType(position: Int): Int {

        if (position == 0) {
            return BANNER
        } else if (position >= 1 && position <= 2) {
            return NAV
        } else if (position == 3) {
            return RECOMMEND_HEAD
        } else if (position>=4 && position <= 7){
            return RECOMMEND
        } else if (position == 8) {
            return ACTION_HEAD
        } else if (position>=9 && position <= 14){
            return ACTION
        } else if (position == 15) {
            return LIST_HEAD
        } else {
            return LIST
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager: RecyclerView.LayoutManager = recyclerView!!.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager: GridLayoutManager = manager
            gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    when (type) {
                        BANNER , RECOMMEND_HEAD , ACTION_HEAD , ACTION_HEAD , LIST_HEAD , LIST-> return 6
                        NAV , RECOMMEND -> return 3
                        ACTION -> return 2
                        else -> return 6
                    }
                }
            }
        }
    }

    companion object {
        val BANNER = 101
        val NAV = 102;
        val RECOMMEND_HEAD = 103
        val RECOMMEND = 104
        val ACTION_HEAD = 105
        val ACTION = 106
        val LIST_HEAD = 107
        val LIST = 108
    }

}
