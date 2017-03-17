package cn.pengxun.vshop.base;

import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.vzan.errorhandler.handler.ResponseErrorListener;
import com.vzan.geetionlib.base.BaseApplication;
import com.vzan.geetionlib.di.module.CacheModule;
import com.vzan.geetionlib.di.module.GlobalConfigModule;
import com.vzan.geetionlib.di.module.ServiceModule;
import com.vzan.geetionlib.http.GlobalHttpHandler;
import com.vzan.geetionlib.utils.UiUtils;

import org.json.JSONArray;
import org.json.JSONException;

import cn.pengxun.vshop.BuildConfig;
import cn.pengxun.vshop.mvp.model.api.Address;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public class VZApplication extends BaseApplication {

    private RefWatcher mRefWatcher;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRefWatcher = !LeakCanary.isInAnalyzerProcess(this) && BuildConfig.USE_CANARY ?
                LeakCanary.install(this) : RefWatcher.DISABLED;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .clientModule(getClientModule())
                .imageModule(getImageModule())
                .globalConfigModule(getGlobalConfigModule())
                .serviceModule(new ServiceModule())
                .cacheModule(new CacheModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 获得leakCanary观察器
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        VZApplication application = (VZApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mRefWatcher != null) this.mRefWatcher = null;
        if (mAppComponent != null) this.mAppComponent = null;
    }

    /**
     * 全局配置信息 使用dagger注入到需要配置的地方
     * @return
     */
    @Override
    protected GlobalConfigModule getGlobalConfigModule() {
        return GlobalConfigModule.builder().baseUrl(Address.APP_DOMAIN).globalHttpHandler(new GlobalHttpHandler() {
            @Override
            public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                // 如果需要再请求服务器之前做一些操作,则重新返回一个做过操作的的requeat如增加header,不做操作则返回request
                // return chain.request().newBuilder().header("token", tokenId)
                // .build();
                return request; // default handler
            }

            @Override
            public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain,
                                                 Response response) {
                // 优先获取http请求结果，并对响应结果做一些全局处理 example：解析json，检测token，重试机制...
                try {
                    if (TextUtils.isEmpty(httpResult)) {
                        // TODO json预处理
                        JSONArray array = new JSONArray(httpResult);
                        Logger.d(array);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return response;
                }

                // 这里如果发现token过期，可以先请求新的token
                // 注意在这个回调之前已经调用过proceed,所以这里必须自己去建立网络请求,如使用okhttp使用新的request去请求
                //Request newRequest = chain.request().newBuilder().header("token", newToken)
                //                            .build();

                // retry the request
                //
                // response.body().close();
                //如果使用okhttp将新的请求,请求成功后,将返回的response  return出去即可

                //如果不需要返回新的结果,则直接把response参数返回出去
                return response;
            }
        }).responseErrorListener(new ResponseErrorListener() {
            @Override
            public void handleResponseError(Context context, Exception e) {
                Logger.w(e.getMessage());
                UiUtils.SnackbarText("网络错误");
            }
        }).build();
    }
}
