package com.bw.movie.wdyy.model;

import android.util.Log;


import com.bw.movie.wdyy.adapter.GZYYBean;
import com.bw.movie.wdyy.adapter.YYLunboAdapter;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.DetailsBean;
import com.bw.movie.wdyy.bean.FindAllMovieCommentBean;
import com.bw.movie.wdyy.bean.GZDYBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.LoginBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;

import com.baway.rikao0411.greendao.gen.DaoMaster;
import com.baway.rikao0411.greendao.gen.DaoSession;

import com.baway.rikao0411.greendao.gen.ZhuceBeanDao;

import com.bw.movie.wdyy.bean.LoginBean;

import com.bw.movie.wdyy.bean.TongzhiBean;
import com.bw.movie.wdyy.bean.TuijianBean;
import com.bw.movie.wdyy.bean.YYLunboBean;
import com.bw.movie.wdyy.bean.YYPiaojiaBean;
import com.bw.movie.wdyy.bean.YypjBean;
import com.bw.movie.wdyy.bean.YyxqBean;
import com.bw.movie.wdyy.bean.ZhuceBean;
import com.bw.movie.wdyy.utile.RetrofitUtil;
import com.bw.movie.wdyy.view.Api;
import com.bw.movie.wdyy.view.App;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
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
    private static int USERID;
    private static String SESSIONID;

    //登录
    public void Login(Map<String, String> map, final MyCallBreak callBreak) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getUtil();
        Api api = retrofitUtil.gets(Api.class);
        api.login("/movieApi/user/v1/login?", map)
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
                            Log.e("aaa", "login: " + json);
                            JSONObject object1 = new JSONObject(json);
                            String m1 = object1.getString("message");
                            callBreak.sressco(m1);

                            //添加数据到数据库
                            Gson gson = new Gson();
                            LoginBean bean = gson.fromJson(json, LoginBean.class);
                            ZhuceBean zhuceBean = new ZhuceBean();
                            zhuceBean.setNickName(bean.getResult().getUserInfo().getNickName());
                            zhuceBean.setBirthday(bean.getResult().getUserInfo().getBirthday());
                            zhuceBean.setHeadPic(bean.getResult().getUserInfo().getHeadPic());
                            zhuceBean.setLastLoginTime(bean.getResult().getUserInfo().getLastLoginTime());
                            zhuceBean.setPhone(bean.getResult().getUserInfo().getPhone());
                            zhuceBean.setSex(bean.getResult().getUserInfo().getSex());
                            Log.e("aaa", "call: " + zhuceBean.getNickName());
                            ZhuceBeanDao daoSession = App.daoSession.getZhuceBeanDao();
                            daoSession.insert(zhuceBean);
                            //将赋值
                            USERID = bean.getResult().getUserId();
                            SESSIONID = bean.getResult().getSessionId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //注册
    public void Zhuce(Map<String, Object> map, final MyCallBreak callBreak) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getUtil();
        Api api = retrofitUtil.gets(Api.class);
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
        Api api = RetrofitUtil.getUtil().gets(Api.class);
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findHotMovieList", USERID + "", SESSIONID, 1, 10)
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


    //查询电影评论
    public void findAllMovieComment(int MovieId, int page, int count, final MyCallBreak myCallBreak) {
        Api api = RetrofitUtil.getUtil().gets(Api.class);
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findReleaseMovieList", USERID + "", SESSIONID, 1, 10)
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.MovieList("/movieApi/movie/v1/findComingSoonMovieList", USERID + "", SESSIONID, 1, 10)
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.YiJianfan("/movieApi/tool/v1/verify/recordFeedBack", USERID, SESSIONID, "很好")
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Banbengeng("/movieApi/tool/v1/findNewVersion", USERID, SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Tuijianyingyuan("/movieApi/cinema/v1/findRecommendCinemas", USERID + "", SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Fujinyingyuan("/movieApi/cinema/v1/findNearbyCinemas", USERID + "", SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.YYMohucaxun("/movieApi/cinema/v1/findAllCinemas", USERID + "", SESSIONID, map)
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Weiguanzhu("/movieApi/cinema/v1/verify/followCinema", USERID, SESSIONID, map)
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Qvxiaoguanzhu("/movieApi/cinema/v1/verify/cancelFollowCinema", USERID, SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.GZYY("/movieApi/cinema/v1/verify/findCinemaPageList", USERID, SESSIONID, map)
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
        Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Chongzhimima("/movieApi/user/v1/verify/modifyUserPwd", USERID, SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.GZDY("/movieApi/movie/v1/verify/findMoviePageList", USERID, SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        Log.e("userid", "yingyuan: " + USERID + SESSIONID);
        gets.XTTZ("/movieApi/tool/v1/verify/findAllSysMsgList", USERID + "", SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        Log.e("userid", "yingyuan: " + USERID + SESSIONID);
        gets.XTTZXXID("/movieApi/tool/v1/verify/changeSysMsgStatus", USERID + "", SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Yingyuanxiangqing("/movieApi/cinema/v1/findCinemaInfo", USERID, SESSIONID, map)
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
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        gets.Yingyuanpingjia("/movieApi/cinema/v1/findAllCinemaComment", USERID, SESSIONID, map)
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

    //电影点赞
    public void DYDZ(Map<String, Object> map, final MyCallBreak callBreak) {
        final Api gets = RetrofitUtil.getUtil().gets(Api.class);
        Log.e("userid","Yijianfan: "+USERID +SESSIONID);
        gets.DYDZ("/movieApi/movie/v1/verify/movieCommentGreat",USERID,SESSIONID,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            //gson.fromJson(json,);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }






        //影院点赞
        public void yingyuandianzan (Map < String, Object > map,final MyCallBreak callBreak){
            final Api gets = RetrofitUtil.getUtil().gets(Api.class);
            gets.Yingyuandianzan("/movieApi/cinema/v1/verify/cinemaCommentGreat", USERID, SESSIONID, map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                Log.e("aaa", "yijian: " + json);
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
            final Api gets = RetrofitUtil.getUtil().gets(Api.class);
            gets.Yingyuanxiepinglun("/movieApi/cinema/v1/verify/cinemaComment", USERID, SESSIONID, map)
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
            final Api gets = RetrofitUtil.getUtil().gets(Api.class);
            gets.YingyuanLunbo("/movieApi/movie/v1/findMovieListByCinemaId", map)
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
            final Api gets = RetrofitUtil.getUtil().gets(Api.class);
            gets.YingyuanPiaojia("/movieApi/movie/v1/findMovieScheduleList", map)
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

        //设置接口
        public interface MyCallBreak {
            public void sressco(Object o);
        }
    }
