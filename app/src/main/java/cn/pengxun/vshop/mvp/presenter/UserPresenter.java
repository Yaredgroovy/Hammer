package cn.pengxun.vshop.mvp.presenter;

import android.app.Application;

import com.vzan.errorhandler.RxErrorHandler;
import com.vzan.errorhandler.handler.ErrorHandlerSubscriber;
import com.vzan.errorhandler.handler.RetryWithDelay;
import com.vzan.geetionlib.base.ActivityManager;
import com.vzan.geetionlib.base.BaseAdapter;
import com.vzan.geetionlib.di.scope.ActivityScope;
import com.vzan.geetionlib.mvp.BasePresenter;
import com.vzan.geetionlib.utils.PermissionUtil;
import com.vzan.geetionlib.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.pengxun.vshop.mvp.contract.UserContract;
import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import cn.pengxun.vshop.mvp.view.adapter.UserAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {
    private RxErrorHandler rxErrorHandler;
    private ActivityManager activityManager;
    private Application mApplication;

    private List<UserEntity> mUsers = new ArrayList<>();
    private BaseAdapter mAdapter;
    private int lastUserId = 1;
    private boolean isFirst = true;

    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View view, RxErrorHandler
            handler, ActivityManager activityManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.activityManager = activityManager;
        this.rxErrorHandler = handler;
        mAdapter = new UserAdapter(mUsers);
        mRootView.setAdapter(mAdapter);
    }

    public void request(final boolean refresh) {
        // 请求外部存储权限
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                // TODO
            }
        }, mRootView.getRxPermissions(), mRootView, rxErrorHandler);
        if (refresh) lastUserId = 1; // 下拉刷新
        // 当下拉刷新时，不需要使用缓存，从网络获取
        boolean isEvictCache = refresh;
        if (refresh && isEvictCache) {
            //默认第一次下拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }
        mModel.getUsers(lastUserId, isEvictCache).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(() -> {
                    if (refresh)
                        mRootView.showLoading(); // 显示下拉刷新的进度条
                    else
                        mRootView.startLoadMore();
                    ; // 显示上拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (refresh)
                            mRootView.hideLoading();
                        else
                            mRootView.endLoadMore();
                    }
                })
                //使用RXlifecycle,使subscription和activity一起销毁
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandlerSubscriber<List<UserEntity>>(rxErrorHandler) {
                    @Override
                    public void onNext(List<UserEntity> userEntities) {
                        // 记录最后一个ID，用于下次请求
                        lastUserId = userEntities.get(userEntities.size() - 1).getId(); //
                        if (refresh) mUsers.clear();
                        mUsers.addAll(userEntities);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAdapter = null;
        this.mUsers = null;
        this.rxErrorHandler = null;
        this.activityManager = null;
        this.mApplication = null;
    }
}
