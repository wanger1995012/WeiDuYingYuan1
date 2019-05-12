package com.bw.movie.wdyy.model;

import android.util.Log;

import com.bw.movie.wdyy.bean.LoginBean;
import com.bw.movie.wdyy.utile.RetrofitUtil;
import com.bw.movie.wdyy.view.Api;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyModel {

    //登录
    public void Login(Map<String,String> map, final MyCallBreak callBreak){
        RetrofitUtil retrofitUtil=RetrofitUtil.getUtil();
        Api api=retrofitUtil.gets(Api.class);
        api.login("/movieApi/user/v1/login?",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            Log.e("aaa", "login: "+json );

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    //注册
    public void Zhuce(Map<String,Object> map, final MyCallBreak callBreak){
        RetrofitUtil retrofitUtil=RetrofitUtil.getUtil();
        Api api=retrofitUtil.gets(Api.class);
        api.Zhuce("/movieApi/user/v1/registerUser",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            Log.e("aaa", "login: "+json );
                            JSONObject object=new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    //设置接口
    public interface MyCallBreak{
        public void sressco(Object o);
    }
}
