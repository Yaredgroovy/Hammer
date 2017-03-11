package com.vzan.errorhandler;

import android.content.Context;

import com.vzan.errorhandler.handler.ErrorHandlerFactory;
import com.vzan.errorhandler.handler.ResponseErrorListener;

/**
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public class RxErrorHandler {
    private ErrorHandlerFactory mHandlerFactory;

    public static Builder builder() {
        return new Builder();
    }

    private RxErrorHandler(Builder builder) {
        this.mHandlerFactory = builder.mErrorHandlerFactory;
    }

    public ErrorHandlerFactory getErrorHandlerFactory() {
        return mHandlerFactory;
    }

    /**
     * 通过builder构建错误模型
     */
    public static final class Builder {
        private Context mContext;
        private ResponseErrorListener mResponseErrorListener;
        private ErrorHandlerFactory mErrorHandlerFactory;

        private Builder() {
        }

        public Builder with(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener responseErroListener) {
            this.mResponseErrorListener = responseErroListener;
            return this;
        }

        public RxErrorHandler build() {
            Preconditions.checkNotNull(mContext, "context is null");
            Preconditions.checkNotNull(mResponseErrorListener, "listener is null");

            this.mErrorHandlerFactory = new ErrorHandlerFactory(mContext, mResponseErrorListener);
            return new RxErrorHandler(this);
        }
    }
}
