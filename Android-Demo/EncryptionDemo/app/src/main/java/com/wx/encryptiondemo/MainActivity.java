package com.wx.encryptiondemo;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.encryptiondemo.base.BaseActivity;
import com.wx.encryptiondemo.databinding.ActivityMainBinding;
import com.wx.encryptiondemo.tool.AESTool;
import com.wx.encryptiondemo.tool.RSATool;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainActivity extends BaseActivity{

    private ActivityMainBinding binding;
    private String AESKEY = "";
    private KeyPair mKeyPair;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {

        //1.AES
        AESKEY = AESTool.getInstance().generateKey();

        binding.btnEncryAes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getText().isEmpty()) {
                    String s = AESTool.getInstance().encrypt(AESKEY,getText());
                    binding.tvEncryText.setText(s);
                }
            }
        });
        binding.btnDecryAes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getText().isEmpty()) {
                    String s = AESTool.getInstance().decrypt(AESKEY,binding.tvEncryText.getText().toString());
                    binding.tvDecryText.setText(s);
                }
            }
        });


        //2.RSA
        mKeyPair = RSATool.getInstance().generateRSAKeyPair();
        //公钥
        final RSAPublicKey publicKey = (RSAPublicKey) mKeyPair.getPublic();
        //私钥
        final RSAPrivateKey privateKey = (RSAPrivateKey) mKeyPair.getPrivate();

        binding.btnPubEnrsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                if (!getText().isEmpty()) {
                    //公钥加密
                    String s = RSATool.getInstance().encryptByPublicKeyForSpilt(getText().getBytes(),publicKey.getEncoded());
                    binding.tvEncryText.setText(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });
        binding.btnPriDersa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!getText().isEmpty()) {
                        //私钥解密
                        String s = RSATool.getInstance().decryptByPrivateKeyForSpilt(binding.tvEncryText.getText().toString(),privateKey.getEncoded());
                        binding.tvDecryText.setText(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.btnPriEnrsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!getText().isEmpty()) {
                        //私钥加密
                        String s = RSATool.getInstance().encryptByPrivateKeyForSpilt(getText().getBytes(),privateKey.getEncoded());
                        binding.tvEncryText.setText(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnPubDersa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!getText().isEmpty()) {
                        //公钥解密
                        String s = RSATool.getInstance().decryptByPublicKeyForSpilt(binding.tvEncryText.getText().toString(),publicKey.getEncoded());
                        binding.tvDecryText.setText(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getText() {
        return binding.edText.getText().toString();
    }

}
