package com.ppdl.md.baseinfo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.ppdl.md.view.TempView;

public class MiBahavior extends CoordinatorLayout.Behavior<Button>{

    private int width;

    public MiBahavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    /**
     *   判断child的布局是否依赖dependency
     *   如果dependency是TempView的实例，说明它就是我们所需要的Dependency
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof TempView;
    }

    /**
     *  每次dependency位置发生变化，都会执行onDependentViewChanged方法
     *  根据dependency的位置，设置Button的位置
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {

        int top = dependency.getTop();
        int left = dependency.getLeft();
        int x = width - left - child.getWidth();
        int y = top;
        setPosition(child,x,y);

        return true;
    }

    private void setPosition(Button child, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y ;
        child.setLayoutParams(layoutParams);
    }

}
