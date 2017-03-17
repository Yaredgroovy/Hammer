package com.vzan.geetionlib.di.module;

import com.vzan.geetionlib.interf.CommonCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;

/**
 * @author liu-feng 
 * @date 20173/13 0013.
 * Email:w710989327@foxmail.com
 */
@Module
public class CacheModule {

    @Singleton
    @Provides
    CommonCache provideCommonCache(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }
}
