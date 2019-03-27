package com.ppdl.md.bottom;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityBottomBinding;

public class BottomActivity extends BaseActivity {

    private ActivityBottomBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottom;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityBottomBinding) binding;
    }

    @Override
    public void InitData() {
        binding.bottomNaviView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(BottomActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_explore:
                        Toast.makeText(BottomActivity.this,"Explore",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting:
                        Toast.makeText(BottomActivity.this,"Setting",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_user:
                        Toast.makeText(BottomActivity.this,"User",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_cloud:
                        Toast.makeText(BottomActivity.this,"Cloud",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}
