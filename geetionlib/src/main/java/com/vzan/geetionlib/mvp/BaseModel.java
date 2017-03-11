package com.vzan.geetionlib.mvp;

import com.vzan.geetionlib.http.BaseCacheManager;
import com.vzan.geetionlib.http.BaseServiceManager;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class BaseModel<S extends BaseServiceManager, C extends BaseCacheManager> implements IModel {
    protected S mServiceManager;
    protected C mCacheManager;

    public BaseModel(S serviceManager, C cacheManager) {
        this.mServiceManager = serviceManager;
        this.mCacheManager = cacheManager;
    }

    @Override
    public void onDestroy() {
        if (mServiceManager != null)
            mServiceManager = null;
        if (mCacheManager != null)
            mCacheManager = null;
    }
}
