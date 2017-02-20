package com.ppdl.rxjava.RxBus;

import android.widget.TextView;

public class TestAsynRequest {

    public void requset(int data){
        RxBus.getInstance().post(110);
    }

    public void requset(String data){
        RxBus.getInstance().post(data);
    }

    public void requset(OneEvent event){
        RxBus.getInstance().post(event);
    }

    public void requset(TextView textView,String data){
        RxBus.getInstance().post(data);
        //textView.setText("UI线程加载");
    }

}
