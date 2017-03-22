package com.vzan.geetionlib.di.module;

import com.vzan.geetionlib.widget.imageloader.BaseImageLoaderStrategy;
import com.vzan.geetionlib.widget.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
@Module
public class ImageModule {

    @Singleton
    @Provides
    BaseImageLoaderStrategy provideImageLoaderStrategy(GlideImageLoaderStrategy
                                                                      glideImageLoaderStrategy) {
        return glideImageLoaderStrategy;
    }
}
