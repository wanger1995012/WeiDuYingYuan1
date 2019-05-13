package com.bw.movie.wdyy.model;

import android.util.Log;

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
                            JSONObject object=new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);

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
