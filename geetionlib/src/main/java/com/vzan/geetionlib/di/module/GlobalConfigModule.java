package com.vzan.geetionlib.di.module;

import android.app.Application;
import android.text.TextUtils;

import com.vzan.errorhandler.handler.ResponseErrorListener;
import com.vzan.geetionlib.http.GlobalHttpHandler;
import com.vzan.geetionlib.utils.DataHelper;
import com.vzan.geetionlib.utils.Preconditions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
@Module
public class GlobalConfigModule {
    private HttpUrl mApiURL;
    private GlobalHttpHandler mHandler;
    private List<Interceptor> mInterceptors;
    private ResponseErrorListener mErrorListener;
    private File mCacheFile;

    private GlobalConfigModule(Builder builder) {
        this.mApiURL = builder.apiUrl;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
        this.mErrorListener = builder.responseErrorListener;
        this.mCacheFile = builder.cacheFile;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Singleton
    @Provides
    List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }


    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mApiURL;
    }


    @Singleton
    @Provides
    GlobalHttpHandler provideGlobeHttpHandler() {
        return mHandler == null ? GlobalHttpHandler.EMPTY : mHandler;//打印请求信息
    }


    /**
     * 提供缓存地址
     */

    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return mCacheFile == null ? DataHelper.getCacheFile(application) : mCacheFile;
    }


    /**
     * 提供处理Rxjava错误的管理器的回调
     *
     * @return
     */
    @Singleton
    @Provides
    ResponseErrorListener provideResponseErroListener() {
        return mErrorListener == null ? ResponseErrorListener.EMPTY : mErrorListener;
    }

    public static final class Builder {
        private HttpUrl apiUrl = HttpUrl.parse("https://api.github.com/");
        private GlobalHttpHandler handler;
        private List<Interceptor> interceptors = new ArrayList<>();
        private ResponseErrorListener responseErrorListener;
        private File cacheFile;

        private Builder() {
        }

        public Builder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl))
                throw new IllegalArgumentException("host is not find");
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        //用来处理http响应结果
        public Builder globalHttpHandler(GlobalHttpHandler handler) {
            this.handler = handler;
            return this;
        }

        //动态添加任意个interceptor
        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        //处理所有Rxjava的onError逻辑
        public Builder responseErrorListener(ResponseErrorListener listener) {
            this.responseErrorListener = listener;
            return this;
        }

        /**
         * 设置缓存目录
         * @param cacheFile
         * @return
         */
        public Builder cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public GlobalConfigModule build() {
            Preconditions.checkNotNull(apiUrl, "baseurl is required");
            return new GlobalConfigModule(this);
        }
    }
}
