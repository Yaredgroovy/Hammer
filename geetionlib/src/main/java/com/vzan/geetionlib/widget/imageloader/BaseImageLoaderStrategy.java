package com.vzan.geetionlib.widget.imageloader;

import android.content.Context;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context ctx, T config);

    void clearImage(Context ctx, T config);
}
