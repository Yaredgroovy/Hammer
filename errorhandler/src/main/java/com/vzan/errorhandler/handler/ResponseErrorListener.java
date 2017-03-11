package com.vzan.errorhandler.handler;

import android.content.Context;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public interface ResponseErrorListener {
    void handleResponseError(Context context, Exception e);

    ResponseErrorListener EMPTY = new ResponseErrorListener() {
        @Override
        public void handleResponseError(Context context, Exception e) {

        }
    };
}
