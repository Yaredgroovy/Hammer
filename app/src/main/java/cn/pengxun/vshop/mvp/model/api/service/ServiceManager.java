package cn.pengxun.vshop.mvp.model.api.service;

import com.vzan.geetionlib.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@Singleton
public class ServiceManager implements BaseServiceManager {

    private ApiService mApiService;

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     * @param apiService
     */
    @Inject
    public ServiceManager(ApiService apiService) {
        mApiService = apiService;
    }

    public ApiService getApiService() {
        return mApiService;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
