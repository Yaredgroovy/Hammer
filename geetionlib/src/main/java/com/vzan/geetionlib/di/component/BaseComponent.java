package com.vzan.geetionlib.di.component;

import com.vzan.geetionlib.base.BaseApplication;
import com.vzan.geetionlib.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
@Singleton
@Component(modules = {AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication application);
}
