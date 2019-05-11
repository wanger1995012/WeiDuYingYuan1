package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.bw.movie.wdyy.utile.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuceActivity extends AppCompatActivity implements ContractInterface.VLogin {

    @BindView(R.id.zhuce_name)
    EditText zhuceName;
    @BindView(R.id.zhuce_sex)
    EditText zhuceSex;
    @BindView(R.id.zhuce_data)
    EditText zhuceData;
    @BindView(R.id.zhuce_phone)
    EditText zhucePhone;
    @BindView(R.id.zhuce_youxiang)
    EditText zhuceYouxiang;
    @BindView(R.id.zhuce_pwd)
    EditText zhucePwd;
    @BindView(R.id.btn_zhuce)
    Button btnZhuce;
    @BindView(R.id.zhuce_pwd2)
    EditText zhucePwd2;
    //P
    ContractInterface.PLogin pLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
        pLogin=new MyPresenter(this);
        //设置点击监听
        btnZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的监听内容
                String name = zhuceName.getText().toString();
                String sex = zhuceSex.getText().toString();
                String data = zhuceData.getText().toString();
                String phone = zhucePhone.getText().toString();
                String youxiang = zhuceYouxiang.getText().toString();
                String pwd = zhucePwd.getText().toString();
                String pwd2 = zhucePwd2.getText().toString();
                //判断性别
                int se;
                if (sex.equals("男")){
                    se=1;
                }else {
                    se=2;
                }
                String encrypt1 = EncryptUtil.encrypt(pwd);
                String encrypt2 = EncryptUtil.encrypt(pwd2);
                //去P层请求
                pLogin.PZhuceInterface(name,phone,encrypt1,encrypt2,se,data,youxiang);
            }
        });
    }

    @Override
    public void login(String str) {

    }

    @Override
    public void VZhuce(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("注册成功")){
            Intent intent=new Intent(ZhuceActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pLogin.onDestory();
        pLogin=null;
    }
}
