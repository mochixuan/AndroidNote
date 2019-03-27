package com.ppdl.rxjava.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context mContext,String data){
        if(mToast==null){
            mToast=Toast.makeText(mContext,data,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(data);
        }
        mToast.show();
    }

}
