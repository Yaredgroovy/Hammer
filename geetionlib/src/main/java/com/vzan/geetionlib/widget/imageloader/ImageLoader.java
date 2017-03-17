package com.vzan.geetionlib.widget.imageloader;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
@Singleton
public final class ImageLoader {
    private BaseImageLoaderStrategy mStrategy;
    @Inject
    public ImageLoader(BaseImageLoaderStrategy strategy){
        this.mStrategy = strategy;
    }

    public <T extends ImageConfig> void loadImage(Context context,T config){
        this.mStrategy.loadImage(context,config);
    }

    public <T extends ImageConfig> void clearImage(Context context, T config) {
        this.mStrategy.clearImage(context, config);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }
}
