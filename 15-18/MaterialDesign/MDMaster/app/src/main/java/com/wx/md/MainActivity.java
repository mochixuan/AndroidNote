package com.wx.md;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnticipateInterpolator;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.wx.md.adapter.pager.MiPageAdapter;
import com.wx.md.base.BaseActivity;
import com.wx.md.databinding.ActivityMainBinding;
import com.wx.md.fragment.OneFragment;
import com.wx.md.fragment.ThreeFragment;
import com.wx.md.fragment.TwoFragment;
import com.wx.md.model.FragBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(@NonNull ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        initToolBar();
        initTabLayout();
    }

    private void initToolBar() {
        setSupportActionBar(binding.drawerMain.toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.drawerMain.toolBar, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //3.
        String[] suggests = new String[]{"Android","H5","Kotlin","Php","Ui"};
        binding.drawerMain.searchView.setVoiceSearch(false);
        binding.drawerMain.searchView.setCursorDrawable(R.drawable.color_cursor_white);
        binding.drawerMain.searchView.setSuggestions(suggests);
        binding.drawerMain.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showToast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //没有美感，只是测试
        binding.drawerMain.toolBar.setAlpha(0);
        binding.drawerMain.toolBar.setScaleX(0.4f);
        binding.drawerMain.toolBar
                .animate()
                .alpha(1f)
                .scaleX(1f)
                .setStartDelay(300)
                .setDuration(900)
                .setInterpolator(new AnticipateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.drawerMain.toolBar.clearAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        binding.drawerMain.toolBar.clearAnimation();
                    }
                });

        //binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.LEFT);      //关闭手势滑动左边
        /*binding.drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的Relativelayt
                //获取抽屉的view
                View mContent = binding.drawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {

                    float leftScale = 1 - 0.3f * scale;
                    //设置左边菜单滑动后的占据屏幕大小
                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    //设置菜单透明度
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    //设置内容界面水平和垂直方向偏转量
                    //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    //设置内容界面操作无效（比如有button就会点击无效）
                    mContent.invalidate();
                    //设置右边菜单滑动后的占据屏幕大小
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.drawerMain.toolBar.clearAnimation();
    }

    private void initTabLayout() {
        List<FragBean> fragments = new ArrayList<>();
        fragments.add(new FragBean("Linear",R.mipmap.icon_chicken_1,new OneFragment()));
        fragments.add(new FragBean("Grid",R.mipmap.icon_fox_2,new TwoFragment()));
        fragments.add(new FragBean("Stagger",R.mipmap.icon_crab_3,new ThreeFragment()));
        MiPageAdapter mPageAdapter = new MiPageAdapter(getSupportFragmentManager(),fragments);
        binding.drawerMain.viewPager.setAdapter(mPageAdapter);
        binding.drawerMain.tabLayout.setupWithViewPager(binding.drawerMain.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        //1
        binding.drawerMain.searchView.setMenuItem(menu.findItem(R.id.menu_search));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                //showToast(String.valueOf(item.getTitle()));
                return true;
            case R.id.menu_tip:
                showToast(String.valueOf(item.getTitle()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //2
        if (binding.drawerMain.searchView.isSearchOpen()) {
            binding.drawerMain.searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

}
