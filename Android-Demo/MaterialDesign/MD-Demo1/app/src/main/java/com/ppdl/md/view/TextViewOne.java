package com.ppdl.md.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ppdl.md.R;

public class TextViewOne extends View{

    private String mMainText;
    private String mSecondText;
    private String mThreeText;

    private int mMainTextSize ;
    private int mSecondTextSize;
    private int mThreeTextSize;

    private int mMainTextColor;
    private int mSecondTextColor;
    private int mThreeTextColor;

    private int mGapX = 2;
    private int mGapY = 10;

    private Paint mPaint;
    private int mHeight;
    private int mWidth;

    public TextViewOne(Context context) {
        this(context,null);
    }

    public TextViewOne(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewOne);
        mMainText = array.getString(R.styleable.TextViewOne_main_text);
        mSecondText = array.getString(R.styleable.TextViewOne_second_text);
        mThreeText = array.getString(R.styleable.TextViewOne_three_text);

        mMainTextSize = (int) array.getDimension(R.styleable.TextViewOne_main_textsize,dptopx(18));
        mSecondTextSize = (int) array.getDimension(R.styleable.TextViewOne_second_textsize,dptopx(10));
        mThreeTextSize = (int) array.getDimension(R.styleable.TextViewOne_three_textsize,dptopx(12));

        mMainTextColor = array.getColor(R.styleable.TextViewOne_main_textcolor, Color.WHITE);
        mSecondTextColor = array.getColor(R.styleable.TextViewOne_second_textcolor, Color.WHITE);
        mThreeTextColor = array.getColor(R.styleable.TextViewOne_three_textcolor, Color.WHITE);

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(mMainTextSize);
        int w1 = (int) mPaint.measureText(mMainText);
        mPaint.setTextSize(mSecondTextSize);
        int w2 = (int) mPaint.measureText(mSecondText);

        int y12 = mHeight/2-mGapY;

        mPaint.setColor(mMainTextColor);
        mPaint.setTextSize(mMainTextSize);
        int x1 = (mWidth-w1-w2)/2;
        canvas.drawText(mMainText,x1-mGapX,y12,mPaint);


        mPaint.setColor(mSecondTextColor);
        mPaint.setTextSize(mSecondTextSize);
        int x2 = (mWidth+w1-w2)/2;
        canvas.drawText(mSecondText,x2+mGapX,y12,mPaint);

        //canvas.drawLine(mWidth/2,0,mWidth/2,mHeight,mPaint);
        //canvas.drawLine(0,mHeight/2,mWidth,mHeight/2,mPaint);

        mPaint.setColor(mThreeTextColor);
        mPaint.setTextSize(mThreeTextSize);
        Rect rect3 = new Rect();
        mPaint.getTextBounds(mThreeText,0,mThreeText.length(),rect3);
        int cx3 = mWidth/2-(rect3.right-rect3.left)/2;
        int cy3 = mHeight/2+rect3.bottom-rect3.top+mGapY;
        canvas.drawText(mThreeText,cx3,cy3,mPaint);
    }

    private int DEFAULT_WIDTH = dptopx(80);
    private int DEFAULT_HEIGHT = dptopx(60);
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = measureHanlder(DEFAULT_WIDTH, widthMeasureSpec);
        int height = measureHanlder(DEFAULT_HEIGHT, heightMeasureSpec);
        setMeasuredDimension(width, height);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
    private int measureHanlder(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        }
        return result;
    }

    public void setmMainText(String mMainText) {
        this.mMainText = mMainText;
    }

    public void setmSecondText(String mSecondText) {
        this.mSecondText = mSecondText;
    }

    public void setmThreeText(String mThreeText) {
        this.mThreeText = mThreeText;
    }

    public void setmMainTextSize(int mMainTextSize) {
        this.mMainTextSize = mMainTextSize;
    }

    public void setmSecondTextSize(int mSecondTextSize) {
        this.mSecondTextSize = mSecondTextSize;
    }

    public void setmThreeTextSize(int mThreeTextSize) {
        this.mThreeTextSize = mThreeTextSize;
    }

    public void setmMainTextColor(int mMainTextColor) {
        this.mMainTextColor = mMainTextColor;
    }

    public void setmSecondTextColor(int mSecondTextColor) {
        this.mSecondTextColor = mSecondTextColor;
    }

    public void setmThreeTextColor(int mThreeTextColor) {
        this.mThreeTextColor = mThreeTextColor;
    }

    private int dptopx(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
