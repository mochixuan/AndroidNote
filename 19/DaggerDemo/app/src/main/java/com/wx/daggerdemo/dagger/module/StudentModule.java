package com.wx.daggerdemo.dagger.module;

import com.wx.daggerdemo.bean.PersonBean;
import com.wx.daggerdemo.bean.StudentBean;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {

    private PersonBean personBean;

    public StudentModule(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Provides
    PersonBean personBeanProvider() {
        return personBean;
    }

    @Provides
    @Named("noParams")
    StudentBean studentBean1Provider() {
        return new StudentBean(new PersonBean());
    }

    @Provides
    @Named("params")
    StudentBean studentBean2Provider(PersonBean personBean) {
        return new StudentBean(personBean);
    }
}
