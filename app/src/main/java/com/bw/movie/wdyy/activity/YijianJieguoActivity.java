package com.bw.movie.wdyy.activity;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.utile.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YijianJieguoActivity extends AppCompatActivity {


    @BindView(R.id.myfankui_fanhui)
    ImageView myfankuiFanhui;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijian_jieguo);
        ButterKnife.bind(this);
        //设置返回的点击事件
        myfankuiFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YijianJieguoActivity.this,ShowActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
                finish();
            }
        });
    }
}
