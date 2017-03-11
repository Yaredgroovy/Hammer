package com.vzan.errorhandler.handler;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * 重试机制
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class RetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {
    private static final String TAG = RetryWithDelay.class.getClass().getSimpleName();
    private final int maxRetries; // 最大次数
    private final int retryDelaySecond; // 延迟时间
    private int retryCount; // 当前重试的次数

    public RetryWithDelay(int maxRetries, int retryDelaySecond) {
        this.maxRetries = maxRetries;
        this.retryDelaySecond = retryDelaySecond;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                if (++retryCount <= maxRetries) {
                    Logger.d(TAG, "出现错误，" + retryDelaySecond + "秒后进行第" + retryCount + "次重连");
                    return Observable.timer(retryDelaySecond, TimeUnit.SECONDS);
                }
                return Observable.error(throwable);
            }
        });
    }
}
