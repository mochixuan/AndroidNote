package com.wx.md.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liuguangqiang.swipeback.SwipeBackLayout;

public abstract class BaseSwipeActivity extends AppCompatActivity implements SwipeBackLayout.SwipeBackListener {

    private static final SwipeBackLayout.DragEdge DEFAULT_DRAG_EDGE = SwipeBackLayout.DragEdge.LEFT;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void setDataBinding(@NonNull ViewDataBinding binding);

    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContainer());
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        swipeBackLayout.addView(view);

        setDataBinding(DataBindingUtil.bind(view));
        initData();
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(DEFAULT_DRAG_EDGE);
        swipeBackLayout.setOnSwipeBackListener(this);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(com.liuguangqiang.swipeback.R.color.black_p50));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        return container;
    }

    public void setEnableSwipe(boolean enableSwipe) {
        swipeBackLayout.setEnablePullToBack(enableSwipe);
    }

    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        ivShadow.setAlpha(1 - fractionScreen);
    }

    public void back(View view) {
        finish();
    }

    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    public void showToast(@NonNull String data) {
        if (data != null && !data.isEmpty()) Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }


}
