package com.ppdl.rxjava.rx.RxBus1;

public class OneEvent {

    String msg;

    public OneEvent(String msg){
        this.msg=msg;
    }

    public String getData(){
        return this.msg;
    }

}
