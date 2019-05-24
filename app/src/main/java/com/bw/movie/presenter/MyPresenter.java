package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.adapter.GZYYBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.bean.ComingSoonBean;

import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.HotMovieListBean;
import com.bw.movie.bean.LoginBean;
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
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.model.MyModel;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */


public class MyPresenter<T> implements ContractInterface.PWXDL,ContractInterface.PDYDZ,ContractInterface.PYYXPL, ContractInterface.PYYDZ, ContractInterface.PXQPL, ContractInterface.PXTTZ, ContractInterface.PXiugaimima, ContractInterface.PGZyy, ContractInterface.PLogin, ContractInterface.PresenterInterface, ContractInterface.PYingyuan, ContractInterface.PGuanzhu {

        T tt;
        MyModel myModel;

        public MyPresenter(T t) {
            myModel = new MyModel();
            this.tt = t;
        }

        //登录
        @Override
        public void PInterface(String phone, String pwd, String pwd2) {
            Map<String, String> map = new HashMap<>();
            map.put("phone", phone);
            map.put("pwd", pwd);
            map.put("pwd2", pwd2);
            myModel.Login(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VLogin vLogin = (ContractInterface.VLogin) tt;
                    LoginBean beans= (LoginBean) o;
                    vLogin.login(beans);
                }
            });
        }

        //注册
        @Override
        public void PZhuceInterface(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String email) {
            Map<String, Object> map = new HashMap<>();
            map.put("nickName", nickName);
            map.put("phone", phone + "");
            map.put("pwd", pwd + "");
            map.put("pwd2", pwd2 + "");
            map.put("sex", sex);
            map.put("birthday", birthday + "");
            map.put("email", email);
            myModel.Zhuce(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VLogin vLogin = (ContractInterface.VLogin) tt;
                    vLogin.VZhuce((String) o);
                }
            });
        }

        @Override
        public void PYijian() {
            myModel.Yijianfan(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYiJian vYiJian = (ContractInterface.VYiJian) tt;
                    vYiJian.VYijian((String) o);
                }
            });
        }

        @Override
        public void PBanben() {
            Map<String, String> map = new HashMap<>();
            map.put("ak", "2");
            myModel.Banbengengxin(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYiJian vYiJian = (ContractInterface.VYiJian) tt;
                    vYiJian.VBanben((String) o);
                }
            });
        }

        @Override
        public void PTuijian(int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", count);
            myModel.TuijianYingyuan(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYingyuan vYingyuan = (ContractInterface.VYingyuan) tt;
                    TuijianBean beans = (TuijianBean) o;
                    vYingyuan.VTuijian(beans.getResult());
                }
            });
        }

        @Override
        public void PFujin(String longitude, String latitude, int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("longitude", longitude);
            map.put("latitude", latitude);
            map.put("page", page);
            map.put("count", count);
            myModel.FujinYingyuan(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYingyuan vYingyuan = (ContractInterface.VYingyuan) tt;
                    TuijianBean beans = (TuijianBean) o;
                    vYingyuan.VFujin(beans.getResult());
                }
            });
        }

        @Override
        public void PYYMhucaxun(String cinemaName, int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaName", cinemaName);
            map.put("page", page);
            map.put("count", count);
            myModel.YYMohucaxun(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYingyuan vYingyuan = (ContractInterface.VYingyuan) tt;
                    TuijianBean beans = (TuijianBean) o;
                    vYingyuan.VYYMohuca(beans.getResult());
                }
            });
        }

        @Override
        public void PWeiguanzhu(int cinemaId) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", cinemaId);
            myModel.Weiguanzhu(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VGuanzhu vGuanzhu = (ContractInterface.VGuanzhu) tt;
                    vGuanzhu.VWeiguanzhu((String) o);
                }
            });
        }

        @Override
        public void PQvxiaoguanzhu(int cinemaId) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", cinemaId);
            myModel.Qvxiaoguanzhu(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VGuanzhu vGuanzhu = (ContractInterface.VGuanzhu) tt;
                    vGuanzhu.VQvxiaoguanzhu((String) o);
                }
            });
        }

        @Override
        public void PGZYY(int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", count);
            myModel.GZYY(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VGZyy vgZyy = (ContractInterface.VGZyy) tt;
                    GZYYBean beans = (GZYYBean) o;
                    vgZyy.VGZYY(beans.getResult());
                }
            });
        }

        //关注电影
        @Override
        public void PGZDY(int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", count);
            myModel.GZDY(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VGZyy vgZyy = (ContractInterface.VGZyy) tt;
                    GZDYBean beans = (GZDYBean) o;
                    vgZyy.VGZDY(beans.getResult());
                }
            });
        }

        //系统通知
        @Override
        public void PXTTZ(int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", count);
            myModel.Xitongtonfzhi(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXTTZ vxttz = (ContractInterface.VXTTZ) tt;
                    TongzhiBean beans = (TongzhiBean) o;
                    vxttz.VXTTZ(beans.getResult());
                }
            });
        }

        //改变系统的状态
        @Override
        public void PXTTZXXID(int xiaoxiID) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", xiaoxiID);
            myModel.XitongtonfzhiXXID(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXTTZ vxttz = (ContractInterface.VXTTZ) tt;
                    vxttz.VXTTZXXID((String) o);
                }
            });
        }

        @Override
        public void Pyyxq(String cinemaId) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", cinemaId);
            myModel.Yingyuanxiangqing(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXQPL vxqpl = (ContractInterface.VXQPL) tt;
                    vxqpl.Vyyxq((YyxqBean) o);
                }
            });
        }

        @Override
        public void PyyLB(String cinemaId) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", cinemaId);
            myModel.Yingyuanlunbo(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXQPL vxqpl = (ContractInterface.VXQPL) tt;
                    YYLunboBean beans = (YYLunboBean) o;
                    vxqpl.VyyLB(beans.getResult());
                }
            });
        }

        @Override
        public void Pyypj(String cinemaId, int page, int count) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", cinemaId);
            map.put("page", page);
            map.put("count", count);
            myModel.Yingyuanpingjia(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXQPL vxqpl = (ContractInterface.VXQPL) tt;
                    YypjBean beans = (YypjBean) o;
                    vxqpl.Vyypj(beans.getResult());
                }
            });
        }

        @Override
        public void Pyypiaojia(String cinemaId, int movieId) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemasId", cinemaId);
            map.put("movieId", movieId);
            myModel.Yingyuanpiaojia(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXQPL vxqpl = (ContractInterface.VXQPL) tt;
                    YYPiaojiaBean beans = (YYPiaojiaBean) o;
                    vxqpl.VyyPiaojia(beans.getResult());
                }
            });

        }

        @Override
        public void PYYXiepinglun(String commentId, String commentContent) {
            Map<String, Object> map = new HashMap<>();
            map.put("cinemaId", commentId);
            map.put("commentContent", commentContent);
            myModel.yingyuanxiepinglun(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYYXPL vyyxpl = (ContractInterface.VYYXPL) tt;
                    vyyxpl.VYYXiepinglun((String) o);
                }
            });
        }

        @Override
        public void onDestory() {
            tt = null;
        }

        @Override
        public void toModelChild1() {
            myModel.ShowMovie(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieListChild1 v = (ContractInterface.ViewMovieListChild1) tt;
                    v.ShowMovieList((HotMovieListBean) o);
                }
            });
        }

        @Override
        public void toModelChild2() {
            myModel.ShowMovie3(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieListChild2 v = (ContractInterface.ViewMovieListChild2) tt;
                    v.ShowMovieList((ComingSoonBean) o);
                }
            });
        }

        @Override
        public void toModelChild3() {
            myModel.ShowMovie2(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieListChild3 v = (ContractInterface.ViewMovieListChild3) tt;
                    v.ShowMovieList((NowPlayingBean) o);
                }
            });
        }

        @Override
        public void toModel1() {
            myModel.ShowMovie(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieList v = (ContractInterface.ViewMovieList) tt;
                    v.ShowMovieList1((HotMovieListBean) o);
                }
            });
        }

        @Override
        public void toModel2() {
            myModel.ShowMovie2(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieList v = (ContractInterface.ViewMovieList) tt;
                    v.ShowMovieList2((NowPlayingBean) o);
                }
            });
        }

        @Override
        public void toModel3() {
            myModel.ShowMovie3(new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.ViewMovieList v = (ContractInterface.ViewMovieList) tt;
                    v.ShowMovieList3((ComingSoonBean) o);
                }
            });
        }

        @Override
        public void toModelQueryMovieInformation(int MovieId) {
            myModel.QueryMovieInformation(MovieId, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.DetailsShow d = (ContractInterface.DetailsShow) tt;
                    //Log.i("tags", "sressco: " + o.toString());
                    d.MovieDetailsShow(o);
                }
            });
        }

        @Override
        public void toModelFindAllMovieComment(int MovieId, int page, int count) {
            myModel.findAllMovieComment(MovieId, page, count, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.FindAllMovieComment f = (ContractInterface.FindAllMovieComment) tt;
                    f.setComment((FindAllMovieCommentBean) o);
                    Log.i("movieComment", "movieComment o =: " + o);
                }
            });
        }

        @Override
        public void toModelSendCounts(int CommentId, String input) {
            Map<String, String> map = new HashMap<>();
            map.put("movieId", CommentId + "");
            map.put("commentContent", input);
            myModel.sendCount(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.DetailsShow d = (ContractInterface.DetailsShow) tt;
                    Log.i("message", "sressco: " + o);
                    d.setPing((String) o);
                }
            });
        }

    @Override
    public void toModelFindYuan(int movieId) {
        myModel.FindYuan(movieId, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.ViewFindYuan v = (ContractInterface.ViewFindYuan) tt;
                v.findYuan((CinemaBean) o);
            }
        });
    }

    @Override
    public void toModelFindSchedule(String schedule, int movieId) {
        myModel.QueryP(schedule, movieId, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.DetailsShow d = (ContractInterface.DetailsShow) tt;
                d.setYuanPiao((ScheduleBean) o);
            }
        });
    }

    @Override
    public void toModelXiaDan(String scheduleId, int amount, String sign) {
        Map<String, Object> map = new HashMap<>();
        map.put("scheduleId", scheduleId + "");
        map.put("amount", amount+"");
        map.put("sign", sign+"");
        myModel.ToXiaDan(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.ViewXiaDan v = (ContractInterface.ViewXiaDan) tt;
                v.XiaDan((XiaDanBean) o);
            }
        });
    }

    @Override
    public void toModelPay(int payType, String orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("payType", payType);
        map.put("orderId", orderId);
        myModel.toPay(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.WXPly wx = (ContractInterface.WXPly) tt;
                wx.WXPly((WXPlyBean) o);
            }
        });
    }

    @Override
    public void toModelPays(int payType, String orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("payType", payType);
        map.put("orderId", orderId);
        myModel.toPay2(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.ZFBPly z = (ContractInterface.ZFBPly) tt;
                z.ZFBPly((String) o);
            }
        });
    }

    public void Pxiugai(String oldpwd, String newpwd, String newpwd2) {
            Map<String, String> map = new HashMap<>();
            map.put("oldPwd", oldpwd);
            map.put("newPwd", newpwd);
            map.put("newPwd2", newpwd2);
            myModel.Chongzhimima(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VXiugaimima xiugaimima = (ContractInterface.VXiugaimima) tt;
                    xiugaimima.Vxiugai((String) o);
                }
            });
        }


        public void PDYDZ(int commentId) {
            Map<String, Object> map = new HashMap<>();
            map.put("commentId", commentId);
            myModel.DYDZ(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VDYDZ vdydz = (ContractInterface.VDYDZ) tt;
                    vdydz.VDYDZ((String) o);
                }
            });
        }


        public void PYYDianzan(int commentId) {
            Map<String, Object> map = new HashMap<>();
            map.put("commentId", commentId);
            myModel.yingyuandianzan(map, new MyModel.MyCallBreak() {
                @Override
                public void sressco(Object o) {
                    ContractInterface.VYYDZ vyydz = (ContractInterface.VYYDZ) tt;
                    vyydz.VYYDianzan((String) o);
                }
            });
        }
    //微信登录
    @Override
    public void PWXDL(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        Log.e("code", "PWXDL: "+code );
        myModel.weixindenglu(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VWXDL vwxdl = (ContractInterface.VWXDL) tt;
                LoginBean beans= (LoginBean) o;
                vwxdl.VWXDL(beans);
            }
        });
    }
}



