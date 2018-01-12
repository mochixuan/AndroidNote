package com.wx.dialogmaster.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by wangxuan on 2018/1/12.
 */

public class TestWidget extends ViewGroup{

    public TestWidget(Context context) {
        super(context);
    }

    public TestWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.d(this.getClass().getSimpleName(),"===========onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(this.getClass().getSimpleName(),"===========onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(this.getClass().getSimpleName(),"===========onDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(this.getClass().getSimpleName(),"===========onSizeChanged");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(this.getClass().getSimpleName(),"===========onFinishInflate");
    }


}
