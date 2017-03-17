package cn.pengxun.vshop.base;

import com.squareup.leakcanary.RefWatcher;
import com.vzan.geetionlib.base.BaseFragment;
import com.vzan.geetionlib.mvp.Presenter;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public abstract class VZFragment<P extends Presenter> extends BaseFragment<P> {

    protected VZApplication mVZApplication;

    @Override
    protected void ComponentInject() {
        mVZApplication = (VZApplication) mActivity.getApplication();
        setupFragmentComponent(mVZApplication.getAppComponent());
    }

    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        //使用leakCanary检测fragment的内存泄漏
        RefWatcher watcher = VZApplication.getRefWatcher(getActivity());
        if (watcher != null)
            watcher.watch(this);
        this.mVZApplication = null;
    }
}
