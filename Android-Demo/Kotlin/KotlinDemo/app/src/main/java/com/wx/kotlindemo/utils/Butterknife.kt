package com.wx.kotlindemo.utils

import android.app.Activity
import com.wx.kotlindemo.interfaces.BindView
import com.wx.kotlindemo.interfaces.LayoutId

object Butterknife {

    //getFields()只能获取public的字段，包括父类的
    //getDeclaredFields()只能获取自己声明的各种字段，包括public，protected，private

    fun inject(activity: Activity) {
        bindContentView(activity)
        bindView(activity)
    }

    private fun bindView(activity: Activity) {
        val target = activity.javaClass
        val fields = target.declaredFields
        //方法取到顶层view，这样我们就可以通过view的
        val decorView = activity.window.decorView
        for (field in fields) {
            //然后遍历成员集合，看是否有标记了@BindView注解的成员
            val bind = field.getAnnotation(BindView::class.java)
            if (bind != null) {
                //对所有属性设置访问权限  当类中的成员变量为private时 必须设置此项
                field.isAccessible = true
                try {
                    //向obj对象的这个Field设置新值value
                    field.set(activity, decorView.findViewById(bind.value))
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun bindContentView(activity: Activity) {
        val target = activity.javaClass
        val layoutId = target.getAnnotation(LayoutId::class.java) as? LayoutId
        if (layoutId != null) {
            activity.setContentView(layoutId.value)
        }
    }

}
