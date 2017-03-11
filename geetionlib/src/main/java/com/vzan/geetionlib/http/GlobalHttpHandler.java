package com.vzan.geetionlib.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http处理
 * @author liu-feng 
 * @date 2017/3/11 0011.
 * Email:w710989327@foxmail.com
 */
public interface GlobalHttpHandler {
    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);

    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);

    GlobalHttpHandler EMPTY = new GlobalHttpHandler() {
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response
                response) {
            return null;
        }

        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            return null;
        }
    };
}
