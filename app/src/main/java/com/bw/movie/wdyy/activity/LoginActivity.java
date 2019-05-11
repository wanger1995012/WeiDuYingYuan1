package com.bw.movie.wdyy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.bw.movie.wdyy.utile.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ContractInterface.VLogin {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.text_zhuce)
    TextView textZhuce;
    @BindView(R.id.btn_login)
    Button btnLogin;
    ContractInterface.PLogin pLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pLogin=new MyPresenter(this);
        //设置登录的点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();
                //加密
               /* EncryptUtil encryptUtil=EncryptUtil.encrypt(pwd);*/
                pLogin.PInterface(phone,pwd);
            }
        });
    }

    @Override
    public void login(String str) {
        if (str.equals("登录成功")){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pLogin.onDestory();
        pLogin=null;
    }
}
