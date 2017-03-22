package cn.pengxun.vshop.mvp.model.api.service;

import java.util.List;

import cn.pengxun.vshop.mvp.model.entity.UserEntity;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public interface ApiService {

    // example
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<UserEntity>> getUsers(@Query("since") int lastIdQueried, @Query("per+page")
            int perPage);

    // TODO 接口调用
}
