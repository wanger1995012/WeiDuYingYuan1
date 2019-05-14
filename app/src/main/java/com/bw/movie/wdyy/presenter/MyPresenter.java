package com.bw.movie.wdyy.presenter;

import com.bw.movie.wdyy.activity.LoginActivity;
import com.bw.movie.wdyy.adapter.GZYYBean;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.bw.movie.wdyy.bean.TuijianBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.model.MyModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyPresenter<T> implements ContractInterface.PGZyy,ContractInterface.PLogin,ContractInterface.PresenterInterface,ContractInterface.PYingyuan,ContractInterface.PGuanzhu {
    T tt;
    MyModel myModel;
    public MyPresenter(T t) {
        myModel=new MyModel();
        this.tt=t;
    }
    //登录
    @Override
    public void PInterface(String phone, String pwd,String pwd2) {
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("pwd",pwd);
        map.put("pwd2",pwd2);
        myModel.Login(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VLogin vLogin= (ContractInterface.VLogin) tt;
                vLogin.login((String) o);
            }
        });
    }
    //注册
    @Override
    public void PZhuceInterface(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String email) {
        Map<String,Object> map=new HashMap<>();
        map.put("nickName",nickName);
        map.put("phone",phone+"");
        map.put("pwd",pwd+"");
        map.put("pwd2",pwd2+"");
        map.put("sex",sex);
        map.put("birthday",birthday+"");
        map.put("email",email);
        myModel.Zhuce(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VLogin vLogin= (ContractInterface.VLogin) tt;
                vLogin.VZhuce((String) o);
            }
        });
    }

    @Override
    public void PYijian() {
        myModel.Yijianfan(new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VYiJian vYiJian= (ContractInterface.VYiJian) tt;
                vYiJian.VYijian((String) o);
            }
        });
    }

    @Override
    public void PBanben() {
        Map<String,String> map=new HashMap<>();
        map.put("ak","2");
        myModel.Banbengengxin(map,new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VYiJian vYiJian= (ContractInterface.VYiJian) tt;
                vYiJian.VBanben((String) o);
            }
        });
    }

    @Override
    public void PTuijian(int page, int count) {
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("count",count);
        myModel.TuijianYingyuan(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VYingyuan vYingyuan= (ContractInterface.VYingyuan) tt;
                TuijianBean beans= (TuijianBean) o;
                vYingyuan.VTuijian(beans.getResult());
            }
        });
    }

    @Override
    public void PWeiguanzhu(int cinemaId) {
        Map<String,Object> map=new HashMap<>();
        map.put("cinemaId",cinemaId);
        myModel.Weiguanzhu(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VGuanzhu vGuanzhu= (ContractInterface.VGuanzhu) tt;
                vGuanzhu.VWeiguanzhu((String) o);
            }
        });
    }

    @Override
    public void PQvxiaoguanzhu(int cinemaId) {
        Map<String,Object> map=new HashMap<>();
        map.put("cinemaId",cinemaId);
        myModel.Qvxiaoguanzhu(map, new MyModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ContractInterface.VGuanzhu vGuanzhu= (ContractInterface.VGuanzhu) tt;
                vGuanzhu.VQvxiaoguanzhu((String) o);
            }
        });
    }

    @Override
    public void PGZYY(int page,int count) {
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("count",count);

    }

    @Override
    public void onDestory() {
        tt=null;
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
}
