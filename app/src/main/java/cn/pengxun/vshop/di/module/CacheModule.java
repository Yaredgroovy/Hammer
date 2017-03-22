package cn.pengxun.vshop.di.module;

import javax.inject.Singleton;

import cn.pengxun.vshop.mvp.model.api.cache.ApiCache;
import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;

/**
 * @author liu-feng 
 * @date 20173/13 0013.
 * Email:w710989327@foxmail.com
 */
@Module
public class CacheModule {

    @Singleton
    @Provides
    ApiCache provideCommonCache(RxCache rxCache) {
        return rxCache.using(ApiCache.class);
    }
}
