package com.vzan.geetionlib.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import static com.vzan.geetionlib.base.BaseActivity.IS_ADD_ACTIVITY_MANAGER_LIST;

/**
 * Activity 生命周期管理
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private ActivityManager mActivityManager;

    public ActivityLifecycle(ActivityManager activityManager){
        this.mActivityManager = activityManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        //如果intent包含了此字段,并且为true说明不加入到list
        // 默认为false,如果不需要管理(比如不需要在退出所有activity(killAll)时，退出此activity就在intent加此字段为true)
        boolean isNotAdd = false;
        if (activity.getIntent() != null)
            isNotAdd = activity.getIntent().getBooleanExtra(IS_ADD_ACTIVITY_MANAGER_LIST, false);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
