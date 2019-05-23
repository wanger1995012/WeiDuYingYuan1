package com.bw.movie.utile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.facebook.common.util.ByteConstants;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class RetrofitUtil {
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    static RetrofitUtil util;
    //私有构造方法
    private RetrofitUtil(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Log.e("aaa", "request: "+request.toString() );
                Response response=chain.proceed(request);
                Log.e("aaa", "response: "+response.toString() );
                return response;
            }
        });
        builder.cache(new Cache(new File("com.bw.cache"),200*ByteConstants.MB));
        okHttpClient=builder.build();
        retrofit=new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static synchronized RetrofitUtil getUtil(){
        if (util==null){
            util=new RetrofitUtil();
        }
        return util;
    }
    public <T>T gets(Class<T> tClass){
        return retrofit.create(tClass);
    }

    public static boolean isNetWork(Context context){
        if(context != null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null){
                return info.isConnected();
            }
        }
        return false;
    }

}
