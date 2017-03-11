package com.vzan.errorhandler.handler;

import android.content.Context;

/**
 * 错误处理 回调
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class ErrorHandlerFactory {

    private Context mContext;
    private ResponseErrorListener mResponseErroListener;

    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErroListener) {
        this.mResponseErroListener = mResponseErroListener;
        this.mContext = mContext;
    }

    /**
     * 错误处理
     * @param throwable
     */
    public void handlerError(Throwable throwable) {
        mResponseErroListener.handleResponseError(mContext, (Exception) throwable);
    }
}
