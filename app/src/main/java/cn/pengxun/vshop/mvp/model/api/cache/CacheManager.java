package cn.pengxun.vshop.mvp.model.api.cache;

import com.vzan.geetionlib.http.BaseCacheManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@Singleton
public class CacheManager implements BaseCacheManager {
    private ApiCache mApiCache;

    /**
     * 如果需要添加Cache只需在构造方法中添加对应的Cache,
     * 在提供get方法返回出去,只要在CacheModule提供了该Cache Dagger2会自行注入
     * @param apiCache
     */
    @Inject
    public CacheManager(ApiCache apiCache) {
        mApiCache = apiCache;
    }

    public ApiCache getApiCache() {
        return mApiCache;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
