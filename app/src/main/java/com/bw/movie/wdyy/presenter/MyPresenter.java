package com.bw.movie.wdyy.presenter;

import com.bw.movie.wdyy.activity.LoginActivity;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.model.MyModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyPresenter<T> implements ContractInterface.PLogin {
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
    public void onDestory() {
        tt=null;
    }
}
