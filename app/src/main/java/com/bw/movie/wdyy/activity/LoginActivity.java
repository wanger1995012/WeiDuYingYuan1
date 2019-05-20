package com.bw.movie.wdyy.activity;

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

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import com.bw.movie.wdyy.utile.EncryptUtil;
import com.bw.movie.wdyy.utile.WeiXinUtil;
import com.bw.movie.wdyy.utile.network.NetBroadcastReceiver;
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
    @BindView(R.id.login_CheckBox)
    android.widget.CheckBox CheckBox;
    private SharedPreferences sp;
    private IntentFilter intentFilter;
    private NetBroadcastReceiver networkChangeReceiver;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;

    //网络变化接受者
    class  NetworkChangeReceiver extends BroadcastReceiver {//网络变化接收者
        @Override
        public void onReceive(Context context, Intent intent) {
            connectivityManager = (ConnectivityManager)getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if(NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()){
                Toast.makeText(LoginActivity.this, "当前网络为WiFi", Toast.LENGTH_SHORT).show();
            } else if (NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()){
                Toast.makeText(LoginActivity.this, "当前网络为移动网络", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this, "当前网络不可用", Toast.LENGTH_SHORT).show();
                //没网
                Intent intent1=new Intent(LoginActivity.this,MeiwangActivity.class);
                startActivity(intent1);
                Log.e("a111", "onCreate: ；没网" );
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //注册广播接收者，监测网络变化
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetBroadcastReceiver();
        registerReceiver(networkChangeReceiver , intentFilter);
        pLogin = new MyPresenter(this);
        if(networkInfo==null ){
            //有网
            initV();
            Log.e("a111", "onCreate: 有网" );
        }else {
            //没网
            Intent intent1=new Intent(LoginActivity.this,MeiwangActivity.class);
            startActivity(intent1);
            Log.e("a111", "onCreate: 11；没网" );
        }
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
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
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
        //取消广播注册
        unregisterReceiver(networkChangeReceiver);
        super.onDestroy();
        pLogin.onDestory();
        pLogin = null;
    }

/*    *//**
     * 判断网络是否连接
     *//*
    private boolean isConnectIsNomarl(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String intentName = info.getTypeName();
            return true;
        }
        return false;
    }*/
}
