package com.bw.movie.wdyy.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.utile.network.NetBroadcastReceiver;

public class MeiwangActivity extends AppCompatActivity {
    NetworkInfo networkInfo;
    private NetBroadcastReceiver networkChangeReceiver;
    private IntentFilter intentFilter;
    //网络变化接受者
    class  NetworkChangeReceiver extends BroadcastReceiver {//网络变化接收者
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if(NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()){
                Toast.makeText(MeiwangActivity.this, "当前网络为WiFi", Toast.LENGTH_SHORT).show();
            } else if (NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()){
                Toast.makeText(MeiwangActivity.this, "当前网络为移动网络", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MeiwangActivity.this, "当前网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meiwang);
        //注册广播接收者，监测网络变化
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetBroadcastReceiver();
        registerReceiver(networkChangeReceiver , intentFilter);
        if(networkInfo==null){
           finish();
        }
    }
}
