package com.wx.proxy.test2;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class Butterknife {

    //getFields()只能获取public的字段，包括父类的
    //getDeclaredFields()只能获取自己声明的各种字段，包括public，protected，private

    public static void inject(Activity activity) {
        bindContentView(activity);
        bindView(activity);
    }

    private static void bindView(Activity activity) {
        Class target = activity.getClass();
        Field[] fields = target.getDeclaredFields();
        //方法取到顶层view，这样我们就可以通过view的
        View decorView = activity.getWindow().getDecorView();
        for (Field field : fields) {
            //然后遍历成员集合，看是否有标记了@BindView注解的成员
            BindView bind = field.getAnnotation(BindView.class);
            if (bind != null) {
                //对所有属性设置访问权限  当类中的成员变量为private时 必须设置此项
                field.setAccessible(true);
                try {
                    //向obj对象的这个Field设置新值value
                    field.set(activity,decorView.findViewById(bind.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void bindContentView(Activity activity) {
        Class target = activity.getClass();
        LayoutId layoutId = (LayoutId) target.getAnnotation(LayoutId.class);
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        }
    }

}
