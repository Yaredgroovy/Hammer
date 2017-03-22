package cn.pengxun.vshop.mvp.model.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public interface ApiCache {

    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<UserEntity>>> getUsers(Observable<List<UserEntity>> oUsers, DynamicKey isLastUserQueried, EvictProvider evictProvider);
}
