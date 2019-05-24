package com.bw.movie.activity;

import android.Manifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import com.bw.movie.utile.EncryptUtil;
import com.bw.movie.utile.network.NetBroadcastReceiver;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

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
    @BindView(R.id.login_CheckBox)
    android.widget.CheckBox CheckBox;
    private SharedPreferences sp;
    private IntentFilter intentFilter;
    private NetBroadcastReceiver networkChangeReceiver;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;
    //广播接受者标识
    private boolean mReceiverTag = false;

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    private SharedPreferences sp1;
    //网络变化接受者
    class  NetworkChangeReceiver extends BroadcastReceiver {//网络变化接收者
        @Override
        public void onReceive(Context context, Intent intent) {
            connectivityManager = (ConnectivityManager)getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null){
                //有网
                Log.e("a111", "onCreate: 有网" );
            }else {
                //没网
                Log.e("a111", "onCreate: 11；没网" );
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //代码中动态注册广播
        if (!mReceiverTag){     //在注册广播接受者的时候 判断是否已被注册,避免重复多次注册广播
            intentFilter = new IntentFilter();
            mReceiverTag = true;    //标识值 赋值为 true 表示广播已被注册
            networkChangeReceiver = new NetBroadcastReceiver();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.registerReceiver(networkChangeReceiver, intentFilter);
        }
        pLogin = new MyPresenter(this);
        initV();
        regToWx();
    }

    private void initV() {
        sp = getSharedPreferences("ssp", MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);

        if (flag){
            String phone = sp.getString("phone", "");
            String pwd = sp.getString("pwd", "");

            editPhone.setText(phone);
            editPwd.setText(pwd);
            CheckBox.setChecked(flag);
        }else {
            editPhone.setText("");
            editPwd.setText("");
            CheckBox.setChecked(flag);
        }


        //设置登录的点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();


                SharedPreferences.Editor edit = sp.edit();
                if (CheckBox.isChecked()){

                    edit.putBoolean("flag",true);
                    edit.putString("phone",phone);
                    edit.putString("pwd",pwd);
                    edit.commit();
                }else {
                    edit.clear();
                }
                edit.apply();
                //加密
                String encryptUtil = EncryptUtil.encrypt(pwd);
                String encryptUtil2 = EncryptUtil.encrypt(pwd);
                Log.e("tag",encryptUtil+"----");

                    pLogin.PInterface(phone,encryptUtil,encryptUtil2);

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
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
            }
        });
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);

        }
    }

    @Override
    public void login(Object o) {
        LoginBean beans= (LoginBean) o;
        String str= beans.getMessage();
        int userId=beans.getResult().getUserId();
        String name=beans.getResult().getUserInfo().getNickName();
        String HeadPic=beans.getResult().getUserInfo().getHeadPic();
        String Phone=beans.getResult().getUserInfo().getPhone();
        int Sex=beans.getResult().getUserInfo().getSex();
        long LastLoginTim=beans.getResult().getUserInfo().getLastLoginTime();
        long Birthday=beans.getResult().getUserInfo().getBirthday();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        if (str.equals("登陆成功")) {

            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            sp1=getSharedPreferences("xinxi", MODE_PRIVATE);

            SharedPreferences.Editor edit = sp1.edit();
                edit.putInt("userId",userId);
                edit.putString("NickName",name);
                edit.putString("HeadPic",HeadPic);
                edit.putString("Phone",Phone);
                edit.putInt("Sex",Sex);
                edit.putLong("LastLoginTime",LastLoginTim);
                edit.putLong("Birthday",Birthday);
                edit.commit();
            startActivity(intent);
        }
    }

    @Override
    public void VZhuce(String str) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiverTag) {   //判断广播是否注册
            mReceiverTag = false;   //Tag值 赋值为false 表示该广播已被注销
            this.unregisterReceiver(networkChangeReceiver);   //注销广播
        }
        pLogin.onDestory();
        pLogin = null;
    }
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID,false);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

}
