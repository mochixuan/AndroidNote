package com.wx.acc

import androidx.databinding.ViewDataBinding
import com.wx.acc.base.BaseActivity
import com.wx.acc.databinding.ActivityMainBinding
import com.wx.acc.sample1.Sample1Activity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initData(binding: ViewDataBinding) {
        this.binding = binding as ActivityMainBinding

        binding.sample1.setOnClickListener { openActivity(Sample1Activity::class.java) }
    }

}

/**
 * 1. LifeCycle: 可以监控Activity、Fragment生命周期。例如：当Activity调用onStart时，可以监控到并且调用对应方法
 * 2. LiveData: 一个数据持有类，当数据改变时，可以监控到,要和LifeCycle绑定。
 *      - LiveData的数据在onStop、onDestroy后还是可以更新的，但不会刷新observe,再次进入会把没反馈的消息会过来
 *      - setValue 主线程调用。postValue主线程调用
 * 3. ViewModel: 也需要和LifeCycle绑定，可以实现Fragment数据共享，屏幕旋转时数据不丢失，无需担心生命周期释放资源问题。
 * 4. Room: 一个SQLite对象映射库数据库。
 */
