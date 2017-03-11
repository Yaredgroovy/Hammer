package com.vzan.geetionlib.mvp;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public interface BaseView {
    /**
     * 显示和隐藏加载动画
     */
    void showLoading();
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String msg);
}
