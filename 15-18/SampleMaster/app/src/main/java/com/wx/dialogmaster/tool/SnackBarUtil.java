package com.wx.dialogmaster.tool;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;

import com.wx.dialogmaster.R;

/**
 * Created by wangxuan on 2017/12/28.
 */

public class SnackBarUtil {

    public static void show(View view,String data,int duration,int backgroundColor,int messageColor) {
        Snackbar.make(view,data,duration).show();
    }

    public static void show(View view,String data,int duration) {
        Snackbar snackbar = Snackbar.make(view,data,duration);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        View childrenView = LayoutInflater.from(snackbarLayout.getContext())
                .inflate(R.layout.snackbar_view,null);

    }

}
