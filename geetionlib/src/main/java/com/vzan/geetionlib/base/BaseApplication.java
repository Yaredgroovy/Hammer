package com.vzan.geetionlib.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.vzan.geetionlib.BuildConfig;
import com.vzan.geetionlib.di.module.AppModule;
import com.vzan.geetionlib.di.module.ClientModule;
import com.vzan.geetionlib.di.module.GlobalConfigModule;
import com.vzan.geetionlib.di.module.ImageModule;
import com.vzan.geetionlib.utils.Preconditions;

import javax.inject.Inject;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public abstract class BaseApplication extends Application {

    private static BaseApplication mApplication;
    private ClientModule mClientModule;
    private AppModule mAppModule;
    private ImageModule mImageModule;
    private GlobalConfigModule mGlobalConfigModule;

    @Inject
    protected ActivityManager mActivityManager;
    @Inject
    protected ActivityLifecycle mActivityLifecycle;

    /**
     * 返回上下文
     * @return
     */
    public static Context getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Logger.init("GeetionLib").logLevel(BuildConfig.LOG_DEBUG ? LogLevel.FULL : LogLevel.NONE);
        this.mAppModule = new AppModule(this);
//        DaggerBaseComponent
//                .builder()
//                .appModule(mAppModule)
//                .build()
//                .inject(this);
        this.mImageModule = new ImageModule(); // 初始化图片加载框架 glide
        this.mClientModule = new ClientModule(mActivityManager);
        this.mGlobalConfigModule = Preconditions.checkNotNull(getGlobalConfigModule(),
                "globalConfigModule is required");
        registerActivityLifecycleCallbacks(mActivityLifecycle);
    }

    /**
     * 程序终止时执行 释放资源
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mClientModule != null)
            this.mClientModule = null;
        if (mAppModule != null)
            mAppModule = null;
        if (mImageModule != null)
            this.mImageModule = null;
        if (mActivityLifecycle != null)
            unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        if (mActivityManager != null) {
            this.mActivityManager.release();
            this.mActivityManager = null;
        }
        if (mApplication != null)
            this.mApplication = null;
    }

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     */
    protected abstract GlobalConfigModule getGlobalConfigModule();

    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }

    public ImageModule getImageModule() {
        return mImageModule;
    }


    public ActivityManager getActivityManager() {
        return mActivityManager;
    }
}
