package com.ppdl.rxjava.RxBus;

public class OneEvent {

    String msg;

    public OneEvent(String msg){
        this.msg=msg;
    }

    public String getData(){
        return this.msg;
    }

}
