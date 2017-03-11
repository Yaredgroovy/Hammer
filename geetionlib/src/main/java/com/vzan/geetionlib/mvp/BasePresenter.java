package com.vzan.geetionlib.mvp;

import org.simple.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class BasePresenter<M extends IModel, V extends BaseView> implements Presenter {
    protected CompositeSubscription mCompositeSubscription;
    protected M mModel;
    protected V mRootView;

    public BasePresenter() {
        onCreate();
    }

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onCreate();
    }

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onCreate();
    }

    /**
     * 是否使用eventBus,默认为使用(false)，
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void onCreate() {
        if (useEventBus())
            EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        if (useEventBus())
            EventBus.getDefault().unregister(this);
        unsubscribe();
        if (mModel != null)
            mModel.onDestroy();
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeSubscription = null;
    }

    /**
     * 订阅事件 ， 将所有的subscription 放入，集中处理
     * @param subscription
     */
    protected void subscribe(Subscription subscription) {
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }

    /**
     * 在activity结束时取消所有正在执行的订阅
     */
    protected void unsubscribe(){
        if (mCompositeSubscription != null)
            mCompositeSubscription.unsubscribe();
    }
}
