package com.bw.movie.wdyy.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.bw.movie.wdyy.utile.EncryptUtil;
import com.bw.movie.wdyy.utile.WeiXinUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

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
    @BindView(R.id.log_weixin)
    ImageView logWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pLogin = new MyPresenter(this);
        //设置登录的点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();
                //加密
                String encryptUtil = EncryptUtil.encrypt(pwd);
                String encryptUtil2 = EncryptUtil.encrypt(pwd);
                //判断网络
                if (isConnectIsNomarl(LoginActivity.this)) {
                    //有网
                    pLogin.PInterface(phone, encryptUtil, encryptUtil2);
                } else {
                    //没网
                    Intent intent = new Intent(LoginActivity.this, MeiwangActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        //设置快速注册点击事件
        textZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });
        //设置微信的点击监听
        logWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 微信登录
                if (!WeiXinUtil.success(LoginActivity.this)) {
                    Toast.makeText(LoginActivity.this, "请先安装应用", Toast.LENGTH_SHORT).show();
                } else {
                    //  验证
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    WeiXinUtil.reg(LoginActivity.this).sendReq(req);
                }
            }
        });
    }

    @Override
    public void login(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        if (str.equals("登陆成功")) {
            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void VZhuce(String str) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pLogin.onDestory();
        pLogin = null;
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String intentName = info.getTypeName();
            return true;
        }
        return false;
    }
}
