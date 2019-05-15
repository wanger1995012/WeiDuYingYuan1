package com.bw.movie.wdyy.view;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    public Observable<ResponseBody> MovieList(@Url String url, @Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count")int count);
    //意见反馈
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> YiJianfan(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@Field("content") String content);
    //版本更新
    @GET
    public Observable<ResponseBody> YiJianfan(@Url String url, @Header("userId") String userId, @Header("sessionId") String sessionId);
    //意见反馈
    @GET
    public Observable<ResponseBody> Banbengeng(@Url String url, @Header("userId") String userId, @Header("sessionId") String sessionId);
    //查询电影信息
    @GET
    public Observable<ResponseBody> QueryMovieInformation (@Url String url, @Header("userId") String userId, @Header("sessionId") String sessionId , @Query("movieId") int movieId);

    public Observable<ResponseBody> Banbengeng(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@HeaderMap Map<String,String>map);
    //推荐影院
    @GET
    public Observable<ResponseBody> Tuijianyingyuan(@Url String url, @Header("userId") String userId, @Header("session") String session,@QueryMap Map<String,Object> map);
    //未关注
    @GET
    public Observable<ResponseBody> Weiguanzhu(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@QueryMap Map<String,Object>map);
    //取消关注
    @GET
    public Observable<ResponseBody> Qvxiaoguanzhu(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@QueryMap Map<String,Object>map);
    //关注影院
    @GET
    public Observable<ResponseBody> GZYY(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@QueryMap Map<String,Object>map);
    //关注影院
    @GET
    public Observable<ResponseBody> GZDY(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@QueryMap Map<String,Object>map);
    //重置密码
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> Chongzhimima(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@FieldMap Map<String,String> map);


}
