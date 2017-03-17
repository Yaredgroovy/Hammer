package com.vzan.geetionlib.di.module;

import com.vzan.geetionlib.interf.CommonService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideApiService(Retrofit retrofit){
        return retrofit.create(CommonService.class);
    }
}
