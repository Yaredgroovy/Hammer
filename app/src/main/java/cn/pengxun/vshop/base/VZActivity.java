package cn.pengxun.vshop.base;

import android.content.Intent;

import com.vzan.geetionlib.base.BaseActivity;
import com.vzan.geetionlib.mvp.Presenter;

import cn.pengxun.vshop.utils.StatusBarUtil;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public abstract class VZActivity<P extends Presenter> extends BaseActivity<P> {
    protected VZApplication mVZApplication;

    @Override
    protected void Initialization() {
        StatusBarUtil.setStatusBarTranslucent(this,false);
    }

    @Override
    protected void ComponentInject() {
        mVZApplication = (VZApplication) getApplication();
        setupActivityComponent(mVZApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mVZApplication = null;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (intent == null) return;
        if (intent.getComponent() == null) return;
        String className = intent.getComponent().getShortClassName();
        if (className.equals("MainActivity")) return;
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (intent == null) return;
        if (intent.getComponent() == null) return;
        String className = intent.getComponent().getShortClassName();
        if (className.equals("MainActivity")) return;
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void finish() {
        super.finish();
        if (this.getClass().getSimpleName().equals("MainActivity")) return;
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
