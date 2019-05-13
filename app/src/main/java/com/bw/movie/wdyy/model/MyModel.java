package com.bw.movie.wdyy.model;

import android.util.Log;


import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.LoginBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;

import com.baway.rikao0411.greendao.gen.DaoMaster;
import com.baway.rikao0411.greendao.gen.DaoSession;

import com.baway.rikao0411.greendao.gen.ZhuceBeanDao;

import com.bw.movie.wdyy.bean.LoginBean;

import com.bw.movie.wdyy.bean.ZhuceBean;
import com.bw.movie.wdyy.utile.RetrofitUtil;
import com.bw.movie.wdyy.view.Api;
import com.bw.movie.wdyy.view.App;
import com.google.gson.Gson;

import org.json.JSONObject;

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
    int USERID=0;
    String SESSIONID=null;
    //登录
    public void Login(Map<String,String> map, final MyCallBreak callBreak){
        RetrofitUtil retrofitUtil=RetrofitUtil.getUtil();
        Api api=retrofitUtil.gets(Api.class);
        Log.e("aaa", "Login: "+map );
        api.login("/movieApi/user/v1/login?",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            JSONObject object=new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                            Log.e("aaa", "login: "+json );
                            JSONObject object1=new JSONObject(json);
                            String m1 = object1.getString("message");
                            callBreak.sressco(m1);

                            //添加数据到数据库
                            Gson gson=new Gson();
                            LoginBean bean = gson.fromJson(json, LoginBean.class);
                            ZhuceBean zhuceBean=new ZhuceBean();
                            zhuceBean.setNickName(bean.getResult().getUserInfo().getNickName());
                            zhuceBean.setBirthday(bean.getResult().getUserInfo().getBirthday());
                            zhuceBean.setHeadPic(bean.getResult().getUserInfo().getHeadPic());
                            zhuceBean.setLastLoginTime(bean.getResult().getUserInfo().getLastLoginTime());
                            zhuceBean.setPhone(bean.getResult().getUserInfo().getPhone());
                            zhuceBean.setSex(bean.getResult().getUserInfo().getSex());
                            Log.e("aaa", "call: "+zhuceBean.getNickName() );
                            ZhuceBeanDao daoSession = App.daoSession.getZhuceBeanDao();
                            daoSession.insert(zhuceBean);
                            //将赋值
                            USERID=bean.getResult().getUserId();
                            SESSIONID=bean.getResult().getSessionId();
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

    public void ShowMovie(final MyCallBreak myCallBreak){
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findHotMovieList",USERID+"",SESSIONID,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            HotMovieListBean hotMovieListBean = gson.fromJson(json, HotMovieListBean.class);
                            myCallBreak.sressco(hotMovieListBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }




    public void ShowMovie2(final MyCallBreak myCallBreak){
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findReleaseMovieList",USERID+"",SESSIONID,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            NowPlayingBean bean = gson.fromJson(json, NowPlayingBean.class);
                            myCallBreak.sressco(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void ShowMovie3(final MyCallBreak myCallBreak){
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findComingSoonMovieList",USERID+"",SESSIONID,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            ComingSoonBean bean = gson.fromJson(json, ComingSoonBean.class);
                            myCallBreak.sressco(bean);
                        } catch (Exception e) {
<<<<<<< HEAD
                            e.printStackTrace();
                        }
                    }
                });
    }
    //意见反馈
    public void Yijianfan(final MyCallBreak callBreak){
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.YiJianfan("/movieApi/tool/v1/verify/recordFeedBack",USERID+"",SESSIONID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "yijian: "+json );
                            JSONObject object=new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    //版本更新
    public void Banbengengxin(final MyCallBreak callBreak){
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Banbengeng("/movieApi/tool/v1/findNewVersion",USERID+"",SESSIONID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "banben: "+json );
                            JSONObject object=new JSONObject(json);
                            String m = object.getString("flag");
                            callBreak.sressco(m);
                        } catch (Exception e) {
=======
>>>>>>> 07d1f0d397702cd844ded6c593d7a061aee96361
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
