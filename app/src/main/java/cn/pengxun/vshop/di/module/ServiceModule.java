package cn.pengxun.vshop.di.module;

import javax.inject.Singleton;

import cn.pengxun.vshop.mvp.model.api.service.ApiService;
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
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
