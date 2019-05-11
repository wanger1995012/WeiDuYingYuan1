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

    @Override
    public void PInterface(String phone, String pwd) {
        Map<String,String> map=new HashMap<>();
    }

    @Override
    public void onDestory() {
        tt=null;
    }
}
