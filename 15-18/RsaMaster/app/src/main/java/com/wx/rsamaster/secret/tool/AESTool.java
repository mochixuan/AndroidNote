package com.wx.rsamaster.secret.tool;

import android.text.TextUtils;

import com.wx.rsamaster.secret.base.BASE64Decoder;
import com.wx.rsamaster.secret.base.BASE64Encoder;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESTool {

    private final String HEX = "0123456789ABCDEF";
    private final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private final String AES = "AES";
    private final String  SHA1PRNG="SHA1PRNG";

    private volatile static AESTool INSTANCE;
    private BASE64Encoder mEncoder;
    private BASE64Decoder mDecoder;

    public static final String KEY = "17DEB8B571832CFB968879EAA3A1CD4F28824D";

    public static AESTool getInstance() {
        if (INSTANCE==null) {
            synchronized (AESTool.class) {
                if (INSTANCE==null) {
                    INSTANCE = new AESTool();
                }
            }
        }
        return INSTANCE;
    }

    //生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
    public String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            String str_key = toHex(bytes_key);
            return str_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }
    private void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    //加密
    public String encrypt(String key, String cleartext) {
        if (TextUtils.isEmpty(cleartext)) {
            return cleartext;
        }
        try {
            byte[] result = encrypt(key,cleartext.getBytes());
            if (mEncoder == null) {
                mEncoder = new BASE64Encoder();
            }
            return mEncoder.encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private byte[] encrypt(String key, byte[] clear){
        byte[] encrypted = null;
        try {
            byte[] raw = getRawKey(key.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            encrypted = cipher.doFinal(clear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    // 对密钥进行处理
    private byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
        } else {
            sr = SecureRandom.getInstance(SHA1PRNG);
        }
        sr.setSeed(seed);
        kgen.init(128, sr); //256 bits or 128 bits,192bits
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    //解密
    public String decrypt(String key, String encrypted) {
        if (TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }
        try {
            if (mDecoder==null) {
                mDecoder = new BASE64Decoder();
            }
            byte[] enc = mDecoder.decodeBuffer(encrypted);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] decrypt(String key, byte[] encrypted) {
        byte[] decrypted = null;
        try {
            byte[] raw = getRawKey(key.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            decrypted = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }

}
