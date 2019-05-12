package com.bw.movie.wdyy.view;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:28
 * @Description：描述信息
 */
public interface Api {
    //登录
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> login(@Url String url, @FieldMap Map<String ,String> map);
    //注册
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> Zhuce(@Url String url,@FieldMap Map<String,Object> map);
    //展示的电影列表
    @GET
    public Observable<ResponseBody> MovieList(@Url String url, @Header("userId") String userId, @Header("session") String session,int page,int count);
}
