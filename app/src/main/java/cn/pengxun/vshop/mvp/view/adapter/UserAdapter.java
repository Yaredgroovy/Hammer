package cn.pengxun.vshop.mvp.view.adapter;

import android.view.View;

import com.vzan.geetionlib.base.BaseAdapter;
import com.vzan.geetionlib.base.BaseHolder;

import java.util.List;

import cn.pengxun.vshop.R;
import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import cn.pengxun.vshop.mvp.view.holder.UserHolder;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
public class UserAdapter extends BaseAdapter<UserEntity> {

    public UserAdapter(List<UserEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<UserEntity> getHolder(View v, int viewType) {
        return new UserHolder(v);
    }

    @Override
    public int getLayoutId(int Type) {
        return R.layout.item_user;
    }
}
