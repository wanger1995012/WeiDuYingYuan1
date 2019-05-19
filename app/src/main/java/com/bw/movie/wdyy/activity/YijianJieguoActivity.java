package com.bw.movie.wdyy.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.utile.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class YijianJieguoActivity extends BaseActivity {


    @BindView(R.id.myfankui_fanhui)
    ImageView myfankuiFanhui;

    @Override
    protected int initLayout() {
        return R.layout.activity_yijian_jieguo;
    }

    @Override
    protected void initView() {
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

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijian_jieguo);
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
    }*/
}
