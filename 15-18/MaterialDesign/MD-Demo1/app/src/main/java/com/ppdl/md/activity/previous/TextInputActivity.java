package com.ppdl.md.activity.previous;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.ppdl.md.R;
import com.ppdl.md.base.BaseActivity;
import com.ppdl.md.databinding.ActivityInputBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextInputActivity extends BaseActivity {

    private ActivityInputBinding binding;
    private String emailFormate = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    private Pattern pattern = Pattern.compile(emailFormate);

    @Override
    public int getLayoutId() {
        return R.layout.activity_input;
    }

    @Override
    public void InitBing(ViewDataBinding binding) {
        this.binding = (ActivityInputBinding) binding;
    }

    @Override
    public void InitData() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkUserName()){
                    binding.userName.setError("用户名输入错误！");
                }else {
                    binding.userName.setError("");
                    //setErrorEnabled();设置错误信息是否显示。true显示，false不显示。
                    if (!checkEmail()){
                        binding.email.setError("邮箱格式错误！");
                    } else {
                        binding.email.setError("");
                    }
                }
            }
        });
    }

    private boolean checkUserName(){
        String userName = binding.userName.getEditText().toString();
        if (userName.trim().equals("")){
            return false;
        }else {
            return true;
        }
    }

    private boolean checkEmail(){
        String email = binding.email.getEditText().getText().toString();
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



}
