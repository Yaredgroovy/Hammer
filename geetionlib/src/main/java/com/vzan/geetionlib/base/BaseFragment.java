package com.vzan.geetionlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vzan.geetionlib.mvp.Presenter;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public abstract class BaseFragment<P extends Presenter> extends RxFragment {
    protected BaseActivity mActivity;
    protected View mRootView;
    @Inject
    protected P mPresenter;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundle(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);
        } else {
            mRootView = inflater.inflate(getResourceId(), container, false);
            ButterKnife.bind(this, mRootView);
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        if (useEventBus())
            EventBus.getDefault().register(this);
        initView();
        ComponentInject();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
        this.mPresenter = null;
        this.mActivity = null;
        this.mRootView = null;
        this.mUnbinder = null;
    }

    protected void onRestartInstance(Bundle savedInstanceState) {
    }

    protected boolean useEventBus() {
        return false;
    }

    /** 依赖注入的入口 */
    protected void initBundle(Bundle bundle) {
    }

    protected abstract void ComponentInject();

    protected abstract int getResourceId();

    protected abstract void initView();

    protected abstract void initData();
}
