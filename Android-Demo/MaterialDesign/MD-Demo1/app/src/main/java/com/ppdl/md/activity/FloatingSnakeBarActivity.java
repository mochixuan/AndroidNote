package com.ppdl.md.activity;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityFloatingBinding;

public class FloatingSnakeBarActivity extends BaseActivity implements View.OnClickListener{

    private ActivityFloatingBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_floating;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityFloatingBinding) binding;
    }

    @Override
    public void InitData() {
        binding.floatAction1.setOnClickListener(this);
        binding.floatAction2.setOnClickListener(this);
        binding.floatAction3.setOnClickListener(this);
        binding.floatAction4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.float_action1:
                snacker1(v);
                break;
            case R.id.float_action2:
                snacker2(v);
                break;
            case R.id.float_action3:
                snacker3(v);
                break;
            case R.id.float_action4:
                snacker4(v);
                break;
        }
    }

    private void snacker1(View v){
        Snackbar.make(v,"样式1",Snackbar.LENGTH_LONG).show();
    }

    private void snacker2(View v){
        //  Snackbar.LENGTH_INDEFINITE  不消失显示，除非手动取消
        Snackbar snackbar = Snackbar.make(v,"样式2",Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    private void snacker3(View v){
        Snackbar.make(v,"只有在CoordinatorLayout布局中才有侧滑效果",Snackbar.LENGTH_LONG).show();
    }

    private void snacker4(View v){
        Snackbar snackbar = Snackbar.make(v,"SnakeBar样式",Snackbar.LENGTH_LONG);

        //设置按钮的样式
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatingSnakeBarActivity.this,"sure",Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setActionTextColor(Color.BLUE);

        //设置显示文字的颜色
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.GREEN);

        //设置背景颜色
        view.setBackgroundColor(Color.GRAY);
        //设置透明度
        view.setAlpha(0.5f);

        //设置位置
        ViewGroup.LayoutParams params = view.getLayoutParams();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(params.width,params.height);
        layoutParams.gravity= Gravity.TOP;

        view.setLayoutParams(layoutParams);

        snackbar.show();

    }

}
