package com.wx.daggerdemo.dagger.component;

import com.wx.daggerdemo.Dagger2Activity;
import com.wx.daggerdemo.dagger.module.OKHttpModule;
import com.wx.daggerdemo.dagger.module.StudentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {OKHttpModule.class, StudentModule.class})
public interface Dagger2ActivityComponent {

    void inject(Dagger2Activity dagger2Activity);

}
