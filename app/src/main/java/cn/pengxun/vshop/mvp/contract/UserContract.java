package cn.pengxun.vshop.mvp.contract;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.vzan.geetionlib.base.BaseAdapter;
import com.vzan.geetionlib.mvp.BaseView;
import com.vzan.geetionlib.mvp.IModel;

import java.util.List;

import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import rx.Observable;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
public interface UserContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);

        void startLoadMore();

        void endLoadMore();

        // 申请权限
        RxPermissions getRxPermissions();
    }

    // model层定义接口，外部只需关心model返回的数据，无需关心具体实现，无需关心来源网络 或缓存
    interface Model extends IModel {
        Observable<List<UserEntity>> getUsers(int lastIdQueried, boolean update);
    }
}
