package com.bw.movie.wdyy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.wdyy.activity.LunboActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image_qidongye)
    ImageView imageQidongye;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //判断是否是第一次登陆
        sp = getSharedPreferences("qidong", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        //第一次启动
        if (isFirst){
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("isFirst",false);
            //提交
            editor.commit();
            new Handler(){
                public void handleMessage(android.os.Message msg) {
                    Intent intent=new Intent(MainActivity.this,LunboActivity.class);
                    startActivity(intent);
                };
            }.sendEmptyMessageDelayed(0, 2000);
        }else {
            //不是第一次启动
            Intent intent=new Intent(MainActivity.this,LunboActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
