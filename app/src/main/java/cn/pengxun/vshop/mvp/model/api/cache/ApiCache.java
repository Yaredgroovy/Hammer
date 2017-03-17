package cn.pengxun.vshop.mvp.model.api.cache;

import com.vzan.geetionlib.interf.CommonCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import rx.Observable;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public interface ApiCache extends CommonCache {

    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<UserEntity>>> getUsers(Observable<List<UserEntity>> oUsers, DynamicKey isLastUserQueried, EvictProvider evictProvider);
}
