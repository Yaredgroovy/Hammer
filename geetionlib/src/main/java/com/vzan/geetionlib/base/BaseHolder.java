package com.vzan.geetionlib.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vzan.geetionlib.utils.KnifeUtil;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View
        .OnClickListener {
    protected OnViewClickListener mOnViewClickListener = null;

    public BaseHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        AutoUtils.autoSize(itemView);  // auto  适配 layout
        KnifeUtil.bindTarget(this, itemView);  // 绑定控件
    }

    /** 设置数据，刷新界面 */
    public abstract void setData(T data, int position);

    /** 释放资源 */
    protected void onRelease() {
    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null)
            mOnViewClickListener.onViewClick(view, this.getPosition());
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }
}
