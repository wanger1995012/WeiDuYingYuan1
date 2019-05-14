package com.bw.movie.wdyy.contract;

import com.bw.movie.wdyy.adapter.GZYYBean;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.GZDYBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.bw.movie.wdyy.bean.TuijianBean;

import java.util.List;

import retrofit2.http.PUT;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class ContractInterface {
    //V
    public interface VLogin{
        //登录
        public void login(String str);
        //注册
        public void VZhuce(String str);
    }
    //P
    public interface PLogin{
        public void PInterface(String phone,String pwd,String pwd2);
        public void PZhuceInterface(String nickName,String phone,String pwd,String pwd2,
                                    int sex,String birthday,String email);
        //意见
        public void PYijian();
        public void PBanben();
        public void onDestory();
    }

    public interface ViewMovieListChild1{
        public void ShowMovieList(HotMovieListBean bean);
    }
    public interface ViewMovieListChild2{
        public void ShowMovieList(ComingSoonBean bean);
    }
    public interface ViewMovieListChild3{
        public void ShowMovieList(NowPlayingBean bean);
    }

    public interface ViewMovieList{
        public void ShowMovieList1(HotMovieListBean bean);
        public void ShowMovieList2(NowPlayingBean bean);
        public void ShowMovieList3(ComingSoonBean bean);
    }

    public interface PresenterInterface{
        public void toModelChild1();
        public void toModelChild2();
        public void toModelChild3();
        public void toModel1();
        public void toModel2();
        public void toModel3();
    }
    //设置意见的V接口，设置版本的V接口
    public interface VYiJian{
        public void VYijian(String str);
        public void VBanben(String str);
    }
    //设置推荐和附近影院的V层接口
    public interface VYingyuan{
        public void VTuijian(List<TuijianBean.ResultBean> list);
    }
    //设置推荐和附近影院的P层接口
    public interface PYingyuan{
        public void PTuijian(int page,int count);
        public void onDestory();
    }
    //设置未关注和取消关注影院的V层接口
    public interface VGuanzhu{
        public void VWeiguanzhu(String str);
        public void VQvxiaoguanzhu(String str);
    }
    //设置未关注和取消关注影院的P层接口
    public interface PGuanzhu{
        public void PWeiguanzhu(int cinemaId);
        public void PQvxiaoguanzhu(int cinemaId);
        public void onDestory();
    }
    //设置关注电影和关注影院的V层接口
    public interface VGZyy{
        public void VGZYY(List<GZYYBean.ResultBean> lst);
        public void VGZDY(List<GZDYBean.ResultBean> lst);
    }
    //设置关注电影和关注影院的P层接口
    public interface PGZyy{
        public void PGZYY(int page,int count);
        public void PGZDY(int page,int count);
        public void onDestory();
    }
    //设置修改密码的V层接口
    public interface VXiugaimima{
        public void Vxiugai(String str);
    }
    //设置修改密码的P层接口
    public interface PXiugaimima{
        public void Pxiugai(String oldPwd,String newpwd,String newpwd2);
        public void onDestory();
    }

}
