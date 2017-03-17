package cn.pengxun.vshop.di.component;

import com.vzan.geetionlib.di.scope.ActivityScope;

import cn.pengxun.vshop.base.AppComponent;
import cn.pengxun.vshop.di.module.UserModule;
import cn.pengxun.vshop.mvp.view.activity.UserActivity;
import dagger.Component;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);
}
