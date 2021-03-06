package com.bw.movie.contract;

import com.bw.movie.adapter.GZYYBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.HotMovieListBean;
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

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class ContractInterface {
    //V
    public interface VLogin{
        //登录
        public void login(Object o);
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
    public interface DetailsShow{
        public void MovieDetailsShow(Object bean);
        public void setPing(String ping);
        public void setYuanPiao(ScheduleBean yuanPiao);
    }
    public interface FindAllMovieComment{
        public void setComment(FindAllMovieCommentBean comment);
    }


    public interface ViewMovieList{
        public void ShowMovieList1(HotMovieListBean bean);
        public void ShowMovieList2(NowPlayingBean bean);
        public void ShowMovieList3(ComingSoonBean bean);
    }

    public interface ViewFindYuan{
        public void findYuan(CinemaBean cinemaBean);
    }

    public interface ViewXiaDan{
        public void XiaDan(XiaDanBean obj);
    }

    public interface WXPly{
        public void WXPly(WXPlyBean bean);
    }

    public interface ZFBPly{
        public void ZFBPly(String str);
    }

    public interface PresenterInterface{
        public void toModelChild1();
        public void toModelChild2();
        public void toModelChild3();
        public void toModel1();
        public void toModel2();
        public void toModel3();
        public void toModelQueryMovieInformation(int MovieId);
        public void toModelFindAllMovieComment(int MovieId,int page,int count);
        public void toModelSendCounts(int CommentId,String input);
        public void toModelFindYuan(int movieId);
        public void toModelFindSchedule(String schedule, int movieId);
        public void toModelXiaDan(String scheduleId,int amount , String sign);
        public void toModelPay(int payType , String orderId);
        public void toModelPays(int payType , String orderId);
    }
    //设置意见的V接口，设置版本的V接口
    public interface VYiJian{
        public void VYijian(String str);
        public void VBanben(String str);
    }
    //设置推荐和附近影院，模糊查询的V层接口
    public interface VYingyuan{
        public void VTuijian(List<TuijianBean.ResultBean> list);
        public void VFujin(List<TuijianBean.ResultBean> list);
        public void VYYMohuca(List<TuijianBean.ResultBean> list);

    }
    //设置推荐和附近影院的P层接口
    public interface PYingyuan{
        public void PTuijian(int page,int count);
        public void PFujin(String longitude,String latitude,int page,int count);
        public void PYYMhucaxun(String cinemaName,int page,int count);
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
    //设置系统通知的V层接口
    public interface VXTTZ{
        public void VXTTZ(List<TongzhiBean.ResultBean> lst);
        public void VXTTZXXID(String str);
    }
    //设置系统通知,改变系统状态的P层接口
    public interface PXTTZ{
        public void PXTTZ(int page,int count);
        public void PXTTZXXID(int xiaoxiID);
        public void onDestory();
    }
    //设置影院详情和评论，轮播旋转木马,票价的V层接口
    public interface VXQPL{
        public void Vyyxq(YyxqBean yyxqBea);
        public void VyyLB(List<YYLunboBean.ResultBean> lst);
        public void VyyPiaojia(List<YYPiaojiaBean.ResultBean> lst);
        public void Vyypj(List<YypjBean.ResultBean> lst);
    }
    //设置影院详情和评论,轮播旋转木马的P层接口
    public interface PXQPL{
        public void Pyyxq(String cinemaId);
        public void PyyLB(String cinemaId);
        public void Pyypj(String cinemaId,int page,int count);
        public void Pyypiaojia(String cinemaId,int movieId);
        public void onDestory();
    }
    //影院点赞的V
    public interface VYYDZ{
        public void VYYDianzan(String str);
    }
    //影院点赞的P
    public interface PYYDZ{
        public void PYYDianzan(int commentId);
        public void onDestory();
    }
    //提交影院评论
    //影院点赞的V
    public interface VYYXPL{
        public void VYYXiepinglun(String str);
    }
    //影院点赞的P
    public interface PYYXPL{
        public void PYYXiepinglun(String commentId,String commentContent);
        public void onDestory();
    }

    //设置电影点赞V
    public interface VDYDZ{
        public void VDYDZ(String str);
    }
    //设置电影点赞P
    public interface PDYDZ{
        public void PDYDZ(int commentId);
    }
    //设置微信登录的V
    public interface VWXDL{
        public void VWXDL(Object obj);
    }
    //设置微信登录的P
    public interface PWXDL{
        public void PWXDL(String code);
    }
    //设置微信登录的V
    public interface VGPJL{
        public void VGPJL(List<MyFoodedBean.ResultBean> lst);
    }
    //设置微信登录的P
    public interface PGPJL{
        public void VGPJL(int page,int count,int status);
    }
    //设置我的信息修改的V
    public interface VWDxiugai{
        public void VWDxiugai(String str);
    }
    //设置我的信息修改的P
    public interface PWDxiugai{
        public void PWDxiugai(String nickName,String sex,String email);
    }
    //电影的关注V
    public interface VDYguanzhu{
        public void VDYguanzhu(String str);
        public void VDYqvxiaoguanzhu(String str);
    }
    //电影的关注V
    public interface PDYguanzhu{
        public void PDYguanzhu(int movieId);
        public void PDYqvxiaoguanzhu(int movieId);
    }
}
