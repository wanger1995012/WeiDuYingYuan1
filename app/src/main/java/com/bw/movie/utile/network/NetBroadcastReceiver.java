package com.bw.movie.utile.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MeiwangActivity;


/**
 * Created by hanbin on 2017/9/12.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
       /* NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();*/
        if(NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()){
            Toast.makeText(context, "当前网络为WiFi", Toast.LENGTH_SHORT).show();
        } else if (NetworkInfo.State.CONNECTED==connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()){
            Toast.makeText(context, "当前网络为移动网络", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "当前网络不可用22", Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(context,MeiwangActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}