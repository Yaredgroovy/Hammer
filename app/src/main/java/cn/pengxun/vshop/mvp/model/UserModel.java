package cn.pengxun.vshop.mvp.model;

import com.vzan.geetionlib.di.scope.ActivityScope;
import com.vzan.geetionlib.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import cn.pengxun.vshop.mvp.contract.UserContract;
import cn.pengxun.vshop.mvp.model.api.cache.CacheManager;
import cn.pengxun.vshop.mvp.model.api.service.ServiceManager;
import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
@ActivityScope
public class UserModel extends BaseModel<ServiceManager, CacheManager> implements UserContract
        .Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public UserModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Observable<List<UserEntity>> getUsers(int lastIdQueried, boolean update) {
        Observable<List<UserEntity>> users = mServiceManager.getApiService().getUsers
                (lastIdQueried, USERS_PER_PAGE);
        // 使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getApiCache().getUsers(users, new DynamicKey(lastIdQueried), new
                EvictDynamicKey(update))
                .flatMap(new Func1<Reply<List<UserEntity>>, Observable<List<UserEntity>>>() {
                    @Override
                    public Observable<List<UserEntity>> call(Reply<List<UserEntity>> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
