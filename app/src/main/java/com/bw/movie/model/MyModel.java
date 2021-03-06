package com.bw.movie.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.bw.movie.adapter.GZYYBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.HotMovieListBean;
import com.bw.movie.bean.Hotbean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MyFoodedBean;
import com.bw.movie.bean.NowPlayingBean;



import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.TongzhiBean;
import com.bw.movie.bean.TuijianBean;
import com.bw.movie.bean.WXPlyBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.YYLunboBean;
import com.bw.movie.bean.YYPiaojiaBean;
import com.bw.movie.bean.YypjBean;
import com.bw.movie.bean.YyxqBean;
import com.bw.movie.bean.ZhuceBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.utile.RetrofitUtil;
import com.bw.movie.utile.T;
import com.bw.movie.view.Api;
import com.bw.movie.view.App;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import freemarker.ext.beans.BeanModel;
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
    private static int USERID;
    private static String SESSIONID;
    RetrofitUtil retrofitUtil = RetrofitUtil.getUtil();
    Api api = RetrofitUtil.getUtil().gets(Api.class);

    //登录
    public void Login(Map<String, String> map, final MyCallBreak callBreak) {
        api.login("/movieApi/user/v1/login?", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            LoginBean bean = gson.fromJson(json, LoginBean.class);

                            callBreak.sressco(bean);

                            Log.e("denglua", "call: "+bean.getResult().getUserInfo().getNickName() );
                            Log.e("denglua", "call: "+bean.getResult().getUserId() );
                            ZhuceBean zhuceBean = new ZhuceBean();
                            zhuceBean.setNickName(bean.getResult().getUserInfo().getNickName());
                            zhuceBean.setBirthday(bean.getResult().getUserInfo().getBirthday());
                            zhuceBean.setHeadPic(bean.getResult().getUserInfo().getHeadPic());
                            zhuceBean.setLastLoginTime(bean.getResult().getUserInfo().getLastLoginTime());
                            zhuceBean.setPhone(bean.getResult().getUserInfo().getPhone());
                            zhuceBean.setSex(bean.getResult().getUserInfo().getSex());

                            Log.e("aaa", "call: " + zhuceBean.getNickName());
                            Log.e("denglua1", "call: " + zhuceBean.nickName);

                            callBreak.sressco(bean);


                            //将赋值
                            USERID = bean.getResult().getUserId();
                            SESSIONID = bean.getResult().getSessionId();

                            //Log.i("userIds", "USERID: ="  + USERID+"");
                            //Log.i("userIds", "SESSIONID:= "  + SESSIONID+"");


                            Log.e("ab123", "call: "+USERID );
                            Log.e("ab123", "call: "+SESSIONID );


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //注册
    public void Zhuce(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Zhuce("/movieApi/user/v1/registerUser", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "login: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    //通过传来的Id去查询电影信息

    public void QueryMovieInformation(int MovieId, final MyCallBreak myCallBreak) {

        api.QueryMovieInformation("/movieApi/movie/v1/findMoviesDetail", USERID + "", SESSIONID, MovieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            DetailsBean bean = gson.fromJson(json, DetailsBean.class);
                            myCallBreak.sressco(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void ShowMovie(final MyCallBreak myCallBreak) {
        api.MovieList("/movieApi/movie/v1/findHotMovieList", USERID + "", SESSIONID, 1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            HotMovieListBean hotMovieListBean = gson.fromJson(json, HotMovieListBean.class);
//                            Hotbean hotbean = (Hotbean) hotMovieListBean.getResult();
                            myCallBreak.sressco(hotMovieListBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    //查询电影评论
    public void findAllMovieComment(int MovieId, int page, int count, final MyCallBreak myCallBreak) {
        api.findAllMovieComment("/movieApi/movie/v1/findAllMovieComment", USERID + "", SESSIONID, MovieId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.i("movieComment", "movieComment json =: " + json);
                            Gson gson = new Gson();
                            FindAllMovieCommentBean findAllMovieCommentBean = gson.fromJson(json, FindAllMovieCommentBean.class);
                            myCallBreak.sressco(findAllMovieCommentBean);
                            Log.i("movieComment", "movieComment findAllMovieCommentBean =: " + findAllMovieCommentBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public void ShowMovie2(final MyCallBreak myCallBreak) {
        api.MovieList("/movieApi/movie/v1/findReleaseMovieList", USERID + "", SESSIONID, 1, 10)
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


    public void ShowMovie3(final MyCallBreak myCallBreak) {
        api.MovieList("/movieApi/movie/v1/findComingSoonMovieList", USERID + "", SESSIONID, 1, 10)
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
                            e.printStackTrace();
                        }
                    }
                });
    }

    //意见反馈
    public void Yijianfan(final MyCallBreak callBreak) {
        api.YiJianfan("/movieApi/tool/v1/verify/recordFeedBack", USERID, SESSIONID, "很好")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "yijian: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //版本更新
    public void Banbengengxin(Map<String, String> map, final MyCallBreak callBreak) {
        api.Banbengeng("/movieApi/tool/v1/findNewVersion", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "banben: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("flag");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //推荐影院
    public void TuijianYingyuan(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Tuijianyingyuan("/movieApi/cinema/v1/findRecommendCinemas", USERID + "", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "tuijian: " + json);
                            Gson gson = new Gson();
                            TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                            callBreak.sressco(tuijianBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //附近影院
    public void FujinYingyuan(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Fujinyingyuan("/movieApi/cinema/v1/findNearbyCinemas", USERID + "", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "fujin: " + json);
                            Gson gson = new Gson();
                            TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                            callBreak.sressco(tuijianBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //模糊
    public void YYMohucaxun(Map<String, Object> map, final MyCallBreak callBreak) {
        api.YYMohucaxun("/movieApi/cinema/v1/findAllCinemas", USERID + "", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("ab", "mohucaxun: " + json);
                            Gson gson = new Gson();
                            TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                            callBreak.sressco(tuijianBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //未关注
    public void Weiguanzhu(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Weiguanzhu("/movieApi/cinema/v1/verify/followCinema", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "weiguanzhu: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //取消关注
    public void Qvxiaoguanzhu(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Qvxiaoguanzhu("/movieApi/cinema/v1/verify/cancelFollowCinema", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "qvxiaoguanzhu: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //关注影院
    public void GZYY(Map<String, Object> map, final MyCallBreak callBreak) {
        api.GZYY("/movieApi/cinema/v1/verify/findCinemaPageList", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "guzhu: " + json);
                            Gson gson = new Gson();
                            GZYYBean beans = gson.fromJson(json, GZYYBean.class);
                            callBreak.sressco(beans);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //重置密码
    public void Chongzhimima(Map<String, String> map, final MyCallBreak callBreak) {
        api.Chongzhimima("/movieApi/user/v1/verify/modifyUserPwd", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "chongzhimima: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //关注电影
    public void GZDY(Map<String, Object> map, final MyCallBreak callBreak) {
        api.GZDY("/movieApi/movie/v1/verify/findMoviePageList", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "guzhu: " + json);
                            Gson gson = new Gson();
                            GZDYBean beans = gson.fromJson(json, GZDYBean.class);
                            callBreak.sressco(beans);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //系统通知
    public void Xitongtonfzhi(Map<String, Object> map, final MyCallBreak callBreak) {
        Log.e("userid", "yingyuan: " + USERID + SESSIONID);
        api.XTTZ("/movieApi/tool/v1/verify/findAllSysMsgList", USERID + "", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "tongzhi: " + json);
                            Gson gson = new Gson();
                            TongzhiBean tongzhiBean = gson.fromJson(json, TongzhiBean.class);
                            callBreak.sressco(tongzhiBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //改变系统消息状态
    public void XitongtonfzhiXXID(Map<String, Object> map, final MyCallBreak callBreak) {
        Log.e("userid", "yingyuan: " + USERID + SESSIONID);
        api.XTTZXXID("/movieApi/tool/v1/verify/changeSysMsgStatus", USERID + "", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "xiaoxiID: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //影院详情
    public void Yingyuanxiangqing(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Yingyuanxiangqing("/movieApi/cinema/v1/findCinemaInfo", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "guzhu: " + json);
                            Gson gson = new Gson();
                            YyxqBean beans = gson.fromJson(json, YyxqBean.class);
                            callBreak.sressco(beans);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //影院评价
    public void Yingyuanpingjia(Map<String, Object> map, final MyCallBreak callBreak) {
        api.Yingyuanpingjia("/movieApi/cinema/v1/findAllCinemaComment", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.e("aaa", "guzhu: " + json);
                            Gson gson = new Gson();
                            YypjBean beans = gson.fromJson(json, YypjBean.class);
                            callBreak.sressco(beans);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //发送
    public void sendCount(Map <String,String> map, final MyCallBreak myCallBreak){
        api.sendCount("/movieApi/movie/v1/verify/movieComment",USERID,SESSIONID,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {

                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String ss = object.getString("message");
                            myCallBreak.sressco(ss);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    //电影点赞
    public void DYDZ(Map<String, Object> map, final MyCallBreak callBreak) {

        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        Log.e("userid","Yijianfan: "+USERID +SESSIONID);
        gets.DYDZ("/movieApi/movie/v1/verify/movieCommentGreat",USERID,SESSIONID,map);

        Api getss = RetrofitUtil.getUtil().gets(Api.class);
        Log.e("userid", "Yijianfan: " + USERID + SESSIONID);
        gets.DYDZ("/movieApi/movie/v1/verify/movieCommentGreat", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            //gson.fromJson(json,);
                            Log.e("aaa", "yijian: " + json);
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
        //影院点赞
        public void yingyuandianzan (Map < String, Object > map,final MyCallBreak callBreak){
            api.Yingyuandianzan("/movieApi/cinema/v1/verify/cinemaCommentGreat", USERID, SESSIONID, map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                Log.e("aaa", "yingyuandianzan: " + json);
                                JSONObject object = new JSONObject(json);
                                String m = object.getString("message");
                                callBreak.sressco(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        //影院写评论
        public void yingyuanxiepinglun (Map < String, Object > map,final MyCallBreak callBreak){
            api.Yingyuanxiepinglun("/movieApi/cinema/v1/verify/cinemaComment", USERID, SESSIONID, map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                Log.e("aaa", "yingyuanxiepinglu: " + json);
                                JSONObject object = new JSONObject(json);
                                String m = object.getString("message");
                                callBreak.sressco(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        //影院轮播
        public void Yingyuanlunbo (Map < String, Object > map,final MyCallBreak callBreak){
            api.YingyuanLunbo("/movieApi/movie/v1/findMovieListByCinemaId", map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                Log.e("a123", "lunbo: " + json);
                                Gson gson = new Gson();
                                YYLunboBean beans = gson.fromJson(json, YYLunboBean.class);
                                callBreak.sressco(beans);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        //影院票价
        public void Yingyuanpiaojia (Map < String, Object > map,final MyCallBreak callBreak){
            api.YingyuanPiaojia("/movieApi/movie/v1/findMovieScheduleList", map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                Log.e("a123", "piaojia: " + json);
                                Gson gson = new Gson();
                                YYPiaojiaBean beans = gson.fromJson(json, YYPiaojiaBean.class);
                                callBreak.sressco(beans);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    //微信登录
    public void weixindenglu (Map < String, Object > map,final MyCallBreak callBreak){
        api.weixindenglu("/movieApi/user/v1/weChatBindingLogin", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson=new Gson();
                            LoginBean beans=gson.fromJson(json,LoginBean.class);
                            String message=beans.getMessage();
                            Log.e("message","111:z"+json);
                            callBreak.sressco(beans);
                            USERID = beans.getResult().getUserId();
                            SESSIONID = beans.getResult().getSessionId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //根据电影Id 查询影院
    public void FindYuan(int movieId , final MyCallBreak myCallBreak){
        api.FindYuan("/movieApi/movie/v1/findCinemasListByMovieId", USERID+"", SESSIONID,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            CinemaBean cinemaBean = gson.fromJson(json, CinemaBean.class);
                            myCallBreak.sressco(cinemaBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //通过电影ID和影院ID查询电影票
    public void QueryP(String y_id, int movieId , final MyCallBreak myCallBreak){
        api.FindPiao("/movieApi/movie/v1/findMovieScheduleList",USERID+"", SESSIONID,y_id+"",movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            ScheduleBean scheduleBean = gson.fromJson(json, ScheduleBean.class);
                            myCallBreak.sressco(scheduleBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
    //去下蛋
    public void ToXiaDan(Map<String,Object> map , final MyCallBreak myCallBreak){
        api.quXiaDan("/movieApi/movie/v1/verify/buyMovieTicket" ,USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            XiaDanBean xiaDanBean = gson.fromJson(json, XiaDanBean.class);
                            myCallBreak.sressco(xiaDanBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    //去支付，根据支付类型和支付的订单号
    public void toPay(Map<String , Object> map,final MyCallBreak myCallBreak){
        api.toPay("/movieApi/movie/v1/verify/pay" ,USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            WXPlyBean wxPlyBean = gson.fromJson(json, WXPlyBean.class);
                            myCallBreak.sressco(wxPlyBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    //去支付，根据支付类型和支付的订单号
    public void toPay2(Map<String , Object> map,final MyCallBreak myCallBreak){
        api.toPay2("/movieApi/movie/v1/verify/pay" ,USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String result = object.getString("result");
                            myCallBreak.sressco(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


    //购票记录
    public void goupiaojilu(Map<String,Object> map , final MyCallBreak myCallBreak){
        api.goupiaojilu("/movieApi/user/v1/verify/findUserBuyTicketRecordList" ,USERID+"", SESSIONID, map)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            MyFoodedBean beans = gson.fromJson(json, MyFoodedBean.class);
                            myCallBreak.sressco(beans);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }



    //我的页面的修改
    public void WDxiugai (Map < String, Object > map,final MyCallBreak callBreak){
        api.WDxiugai("/movieApi/user/v1/verify/modifyUserInfo", USERID, SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    //电影关注
    public void DYguanzhu (Map < String, Object > map,final MyCallBreak callBreak){
        /*api.DYguanzhu("/movieApi/movie/v1/verify/followMovie", USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });*/
        String url="/movieApi/movie/v1/verify/followMovie";
        MyApi(url,map,callBreak);
    }
    //电影取消关注
    public void DYqvxiaoguanzhu (Map < String, Object > map,final MyCallBreak callBreak){
       /* api.DYqvxiaoguanzhu("/movieApi/movie/v1/verify/cancelFollowMovie", USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });*/
        String url="/movieApi/movie/v1/verify/cancelFollowMovie";
       MyApi(url,map,callBreak);
    }
    //设置接口
    public interface MyCallBreak {
        public void sressco(Object o);
    }
    //设置公共的方法封装
    private void MyApi(String url,Map<String,Object> map, final MyCallBreak callBreak){
        api.Post(url, USERID+"", SESSIONID, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            JSONObject object = new JSONObject(json);
                            String m = object.getString("message");
                            callBreak.sressco(m);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
   /* //设置公共的方法封装
    private void getMyApi(String url, Map<String,Object> map, final MyCallBreak callBreak, final Object a1){
        api.Get(url, USERID+"", SESSIONID,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            Class<T> a= (Class<T>) a1;
                            Object tuijianBean = gson.fromJson(json, a.getClass());
                            callBreak.sressco(tuijianBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }*/

}
