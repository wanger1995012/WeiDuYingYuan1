package com.bw.movie.wdyy.contract;

import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;

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
        public void onDestory();
    }

    public interface ViewMovieList{
        public void ShowMovieList1(HotMovieListBean bean);
        public void ShowMovieList2(NowPlayingBean bean);
        public void ShowMovieList3(ComingSoonBean bean);
    }

    public interface PresenterInterface{
        public void toModel1();
        public void toModel2();
        public void toModel3();
    }

}
