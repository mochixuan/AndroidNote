package com.ppdl.rxjava.rx.RxBus1;

import android.widget.TextView;

public class TestAsynRequest {

    public void requset(int data){
        RxBus1.getInstance().post(110);
    }

    public void requset(String data){
        RxBus1.getInstance().post(data);
    }

    public void requset(OneEvent event){
        RxBus1.getInstance().post(event);
    }

    public void requset(TextView textView,String data){
        RxBus1.getInstance().post(data);
        //textView.setText("UI线程加载");
    }

}
