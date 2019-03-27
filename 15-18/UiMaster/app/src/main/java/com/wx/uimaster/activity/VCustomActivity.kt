package com.wx.uimaster.activity

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper
import com.wx.uimaster.R
import com.wx.uimaster.adapter.vlayout.CustomAdapter
import com.wx.uimaster.base.BaseActivity
import com.wx.uimaster.databinding.ActivityVcustomBinding
import com.wx.uimaster.model.ModelThree

class VCustomActivity : BaseActivity() {

    private lateinit var binding: ActivityVcustomBinding

    override fun layoutId(): Int {
        return R.layout.activity_vcustom
    }

    override fun setDataBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityVcustomBinding
    }

    override fun initData() {
        //初始化LayoutManager
        val vlayoutManager =  VirtualLayoutManager(this)
        binding.recyclerView.layoutManager = vlayoutManager as RecyclerView.LayoutManager?

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(0,10)
        binding.recyclerView.recycledViewPool = viewPool

        //设置Adapter
        val delegateAdapter = DelegateAdapter(vlayoutManager,true)
        binding.recyclerView.adapter = delegateAdapter

        //设置自己的适配器
        //val gridlayoutHelper = GridLayoutHelper(3); //可以设置间隙等许多配置
        //gridlayoutHelper.setAutoExpand(false) 可以防止最后几个拉伸
        //val customAdapter = CustomAdapter(getModelThreeData(), gridlayoutHelper)
        var staggeredHelper = StaggeredGridLayoutHelper(3)
        val customAdapter = CustomAdapter(getModelThreeData(), staggeredHelper)
        delegateAdapter.addAdapter(customAdapter)
    }

    fun getModelThreeData(): List<ModelThree> {

        val width = getWindowManager().getDefaultDisplay().getWidth()
        val scaleSize = 200

        val modelThresss = arrayListOf<ModelThree>()


        modelThresss.add(ModelThree("秦时明月","秦时明月", intArrayOf(R.mipmap.ic_qsmy_1,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("万里长城","万里长城",intArrayOf(R.mipmap.ic_qsmy_2,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("君临天下","君临天下",intArrayOf(R.mipmap.ic_qsmy_3,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("天行九歌","天行九歌",intArrayOf(R.mipmap.ic_qsmy_4,((width/2-Math.random()*scaleSize).toInt()))))


        modelThresss.add(ModelThree("秦时明月","12元", intArrayOf(R.mipmap.ic_qsmy_5,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("万里长城","23元",intArrayOf(R.mipmap.ic_qsmy_6,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("君临天下","45元",intArrayOf(R.mipmap.ic_qsmy_7,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("天行九歌","67元",intArrayOf(R.mipmap.ic_qsmy_8,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("天域","89元",intArrayOf(R.mipmap.ic_qsmy_9,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("武庚记","61元",intArrayOf(R.mipmap.ic_qsmy_10,((width/2-Math.random()*scaleSize).toInt()))))

        modelThresss.add(ModelThree("火影忍者","\t\t这是一个忍者的世界.从小身上封印着邪恶的九尾妖狐,鸣人受尽了村人的冷落,只是拼命用各种恶作剧试图吸引大家的注意力.", intArrayOf(R.mipmap.ic_qsmy_1,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("东京食尸鬼","\t\t蔓延着一种吞食死尸的怪人,人们称之为食尸鬼(喰种)",intArrayOf(R.mipmap.ic_qsmy_2,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("斩赤红之瞳","\t\t少年剑士塔兹米为了解救一座贫穷的村子和伙伴一起渠道帝都打拼",intArrayOf(R.mipmap.ic_qsmy_3,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("海贼王","\t\t航海王《ONE PIECE》是日本漫画家尾田荣一郎作画的少年漫画作品，简称OP，在《周刊少年Jump》1997年34号开始连载",intArrayOf(R.mipmap.ic_qsmy_4,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("天书传奇","\t\t是由史晨赟编剧，石家庄市深度动画科技有限公司拍摄的动画电影",intArrayOf(R.mipmap.ic_qsmy_5,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("银魂","\t\t作为以SF时代剧为体裁而创作的漫画，也是新人作者空知英秋的首部连载作品",intArrayOf(R.mipmap.ic_qsmy_6,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("神奇宝贝","\t\t皮卡丘已成功打入全世界数十个国家，成为世界闻名的卡通形象和日本的国民动画",intArrayOf(R.mipmap.ic_qsmy_8,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("网球王子","\t\t在漫画被改编成动画片播出后，日本一度掀起了网球热潮",intArrayOf(R.mipmap.ic_qsmy_9,((width/2-Math.random()*scaleSize).toInt()))))
        modelThresss.add(ModelThree("蜡笔小新","\t\t蜡笔小新受欢迎程度，可以由故事发生地崎玉县春日市采纳了他作为宣传人物而得知。",intArrayOf(R.mipmap.ic_qsmy_10,((width/2-Math.random()*scaleSize).toInt()))))

        return modelThresss
    }

}
