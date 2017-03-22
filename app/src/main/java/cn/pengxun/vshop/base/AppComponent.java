package cn.pengxun.vshop.base;

import android.app.Application;

import com.vzan.errorhandler.RxErrorHandler;
import com.vzan.geetionlib.base.ActivityManager;
import com.vzan.geetionlib.di.module.AppModule;
import com.vzan.geetionlib.di.module.ClientModule;
import com.vzan.geetionlib.di.module.GlobalConfigModule;
import com.vzan.geetionlib.di.module.ImageModule;
import com.vzan.geetionlib.widget.imageloader.ImageLoader;

import javax.inject.Singleton;

import cn.pengxun.vshop.di.module.CacheModule;
import cn.pengxun.vshop.di.module.ServiceModule;
import cn.pengxun.vshop.mvp.model.api.cache.CacheManager;
import cn.pengxun.vshop.mvp.model.api.service.ServiceManager;
import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, ImageModule.class,
        CacheModule.class, GlobalConfigModule.class})
public interface AppComponent {
    Application Application();

    // retrofit API services
    ServiceManager serviceManager();

    // cache manager
    CacheManager cacheManager();

    // rx error manager
    RxErrorHandler rxErrorHandler();

    OkHttpClient okHttpClient();

    // 图片管理器，使用策略模式，默认glide 可替换框架
    ImageLoader imageLoader();

    // activity manager
    ActivityManager activityManager();
}
