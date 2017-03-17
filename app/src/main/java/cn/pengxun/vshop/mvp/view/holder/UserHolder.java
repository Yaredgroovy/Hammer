package cn.pengxun.vshop.mvp.view.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.vzan.geetionlib.base.BaseHolder;
import com.vzan.geetionlib.widget.imageloader.ImageLoader;
import com.vzan.geetionlib.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import cn.pengxun.vshop.R;
import cn.pengxun.vshop.base.VZApplication;
import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import rx.Observable;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
public class UserHolder extends BaseHolder<UserEntity> {
    @Nullable
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @Nullable
    @BindView(R.id.tv_name)
    TextView tvName;

    private ImageLoader mImageLoader;
    private final VZApplication mApplication;

    public UserHolder(View itemView) {
        super(itemView);
        mApplication = (VZApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(UserEntity data, int position) {
        Observable.just(data.getUsername()).subscribe(RxTextView.text(tvName));
        mImageLoader.loadImage(mApplication, GlideImageConfig.builder().url(data.getAvatarUrl())
                .imageView(ivAvatar).build());
    }

    @Override
    protected void onRelease() {
        super.onRelease();
        mImageLoader.clearImage(mApplication, GlideImageConfig.builder().imageView(ivAvatar)
                .build());

    }
}
