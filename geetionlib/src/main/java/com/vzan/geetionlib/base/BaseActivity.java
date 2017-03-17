package com.vzan.geetionlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vzan.geetionlib.mvp.Presenter;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public abstract class BaseActivity<P extends Presenter> extends RxAppCompatActivity {

    protected BaseApplication mApplication;
    private Unbinder mUnbinder;

    @Inject
    protected P mPresenter;
    // 是否加入到ActivityManager中统一管理
    public static final String IS_ADD_ACTIVITY_MANAGER_LIST = "is_add_activity_manager_list";
    // 鸿洋大神的AutoLayout
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (BaseApplication) getApplication();
        if (useEventBus())
            EventBus.getDefault().register(this);
        Initialization();
        setContentView(getResourceId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        ComponentInject(); // 依赖注入
        initData();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        if (useEventBus()) EventBus.getDefault().unregister(this);
        this.mPresenter = null;
        this.mUnbinder = null;
        this.mApplication = null;
    }

    protected boolean useEventBus() {
        return false;
    }

    /** 初始化一些任务 */
    protected abstract void Initialization();

    /** 依赖注入的入口 */
    protected abstract void ComponentInject();

    protected abstract int getResourceId();

    protected abstract void initView();

    protected abstract void initData();
}
