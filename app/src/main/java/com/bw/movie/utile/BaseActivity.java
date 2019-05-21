package com.bw.movie.utile;


/**
 * Created by hb on 16/3/29.
 */
public abstract class BaseActivity {

   /* @Override
    public void onChangeListener(int status) {
        BaseActivity listener = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            NetBroadcastReceiver netBroadcastReceiver = new NetBroadcastReceiver();
            //注册广播接收
            registerReceiver(netBroadcastReceiver, filter);
        }
        if (status==0) {
            Toast.makeText(this, "移动网络", Toast.LENGTH_SHORT).show();
        } else if (status==1) {
            Toast.makeText(this, "无线网络", Toast.LENGTH_SHORT).show();
        } else if (status==-1) {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
    }*/

}
