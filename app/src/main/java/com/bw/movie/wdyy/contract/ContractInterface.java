package com.bw.movie.wdyy.contract;

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
}
