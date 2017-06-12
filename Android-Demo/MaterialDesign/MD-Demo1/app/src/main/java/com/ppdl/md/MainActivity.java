package com.ppdl.md;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.ppdl.md.activity.anim.Anim1Activity;
import com.ppdl.md.activity.previous.AppBarActivity;
import com.ppdl.md.activity.previous.AppBarLayout1;
import com.ppdl.md.activity.previous.CoordinatorActivity;
import com.ppdl.md.activity.previous.DrawerNaviActivity;
import com.ppdl.md.activity.previous.FloatingSnakeBarActivity;
import com.ppdl.md.activity.previous.RevelAniActivity;
import com.ppdl.md.activity.previous.RippleActitvity;
import com.ppdl.md.activity.previous.ScreenActivity;
import com.ppdl.md.activity.previous.SheetActivity;
import com.ppdl.md.activity.previous.SheetDialogActivity;
import com.ppdl.md.activity.previous.SwitchCompatActivity;
import com.ppdl.md.activity.previous.TabLayoutActivity;
import com.ppdl.md.activity.previous.TextInputActivity;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.bottom.BottomActivity;
import com.ppdl.md.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ActivityMainBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void InitData() {
        binding.btnAnim.setOnClickListener(this);
        binding.btnCoordinator.setOnClickListener(this);
        binding.btnApptablayout.setOnClickListener(this);
        binding.btnApptablayout1.setOnClickListener(this);
        binding.btnDrawer.setOnClickListener(this);
        binding.btnFloat.setOnClickListener(this);
        binding.btnTabLayout.setOnClickListener(this);
        binding.btnTextinput.setOnClickListener(this);
        binding.btnRipple.setOnClickListener(this);
        binding.btnRevel.setOnClickListener(this);
        binding.btnScreen.setOnClickListener(this);
        binding.btnSheets.setOnClickListener(this);
        binding.btnSheetsDialog.setOnClickListener(this);
        binding.btnSwitch.setOnClickListener(this);
        binding.btnBottomnavi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_anim:
                openActitivity(Anim1Activity.class);
                break;
            case R.id.btn_coordinator:
                openActitivity(CoordinatorActivity.class);
                break;
            case R.id.btn_apptablayout:
                openActitivity(AppBarActivity.class);
                break;
            case R.id.btn_apptablayout1:
                openActitivity(AppBarLayout1.class);
                break;
            case R.id.btn_drawer:
                openActitivity(DrawerNaviActivity.class);
                break;
            case R.id.btn_float:
                openActitivity(FloatingSnakeBarActivity.class);
                break;
            case R.id.btn_tabLayout:
                openActitivity(TabLayoutActivity.class);
                break;
            case R.id.btn_textinput:
                openActitivity(TextInputActivity.class);
                break;
            case R.id.btn_ripple:
                openActitivity(RippleActitvity.class);
                break;
            case R.id.btn_revel:
                openActitivity(RevelAniActivity.class);
                break;
            case R.id.btn_screen:
                openActitivity(ScreenActivity.class);
                break;
            case R.id.btn_sheets:
                openActitivity(SheetActivity.class);
                break;
            case R.id.btn_sheets_dialog:
                openActitivity(SheetDialogActivity.class);
                break;
            case R.id.btn_switch:
                openActitivity(SwitchCompatActivity.class);
                break;
            case R.id.btn_bottomnavi:
                openActitivity(BottomActivity.class);
                break;
        }
    }

}
