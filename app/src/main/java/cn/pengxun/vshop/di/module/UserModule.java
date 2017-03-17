package cn.pengxun.vshop.di.module;

import com.vzan.geetionlib.di.scope.ActivityScope;

import cn.pengxun.vshop.mvp.contract.UserContract;
import cn.pengxun.vshop.mvp.model.UserModel;
import dagger.Module;
import dagger.Provides;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
@Module
public class UserModule {
    private UserContract.View view;

    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView() {
        return view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model) {
        return model;
    }
}
