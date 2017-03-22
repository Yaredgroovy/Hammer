package cn.pengxun.vshop.mvp.view.activity;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.vzan.geetionlib.base.BaseAdapter;
import com.vzan.geetionlib.utils.UiUtils;
import com.vzan.geetionlib.widget.refreshlayout.SwipeRefreshLayout;
import com.vzan.geetionlib.widget.refreshlayout.listener.OnLoadMoreListener;
import com.vzan.geetionlib.widget.refreshlayout.listener.OnRefreshListener;

import butterknife.BindView;
import cn.pengxun.vshop.R;
import cn.pengxun.vshop.base.AppComponent;
import cn.pengxun.vshop.base.VZActivity;
import cn.pengxun.vshop.di.component.DaggerUserComponent;
import cn.pengxun.vshop.di.module.UserModule;
import cn.pengxun.vshop.mvp.contract.UserContract;
import cn.pengxun.vshop.mvp.presenter.UserPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author liu-feng
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public class UserActivity extends VZActivity<UserPresenter> implements UserContract.View,
        OnRefreshListener,
        OnLoadMoreListener {
    @Nullable
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RxPermissions rxPermissions;
    private Paginate mPaginate;
    private boolean isLoadingMore;

    @Override
    protected int getResourceId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mPresenter.request(true);
    }

    @Override
    public RxPermissions getRxPermissions() {
        return rxPermissions;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        this.rxPermissions = new RxPermissions(this);
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {
        Observable.just(1).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String msg) {
        UiUtils.SnackbarText(msg);
    }

    @Override
    public void onRefresh() {
        mPresenter.request(true);
    }

    @Override
    public void onLoadMore() {
        mPresenter.request(false);
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setOnLoadMoreListener(this);
        UiUtils.configRecycleView(mRecyclerView, new GridLayoutManager(this, 2));
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.request(false);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };
            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    protected void onDestroy() {
        BaseAdapter.releaseAllHolder(mRecyclerView);
        super.onDestroy();
        this.rxPermissions = null;
        this.mPaginate = null;
    }
}
