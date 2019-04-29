package com.wx.daggerdemo.bean;

import android.util.Log;

import javax.inject.Inject;

public class StudentBean {

    private String name = "mochixuan";

    private String sex;

    private int years;

    private PersonBean personBean;

    @Inject
    public StudentBean(PersonBean personBean) {
        Log.d(getClass().getSimpleName(),"=======Init StudentBean>>");
        this.personBean = personBean;
    }

    public StudentBean(PersonBean personBean,String name, String sex, int years) {
        this.name = name;
        this.sex = sex;
        this.years = years;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
