package com.bw.movie.wdyy.activity;

<<<<<<< HEAD

import android.content.Intent;
import android.os.Bundle;
=======
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 9234417e158775ca1bb6653706e5b1c712ef6d6a
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.wdyy.R;
<<<<<<< HEAD
import com.bw.movie.wdyy.utile.BaseActivity;
=======
>>>>>>> 9234417e158775ca1bb6653706e5b1c712ef6d6a

import butterknife.BindView;
import butterknife.ButterKnife;

<<<<<<< HEAD

public class YijianJieguoActivity extends BaseActivity {

=======
public class YijianJieguoActivity extends AppCompatActivity {
>>>>>>> 9234417e158775ca1bb6653706e5b1c712ef6d6a

    @BindView(R.id.myfankui_fanhui)
    ImageView myfankuiFanhui;

    @Override
<<<<<<< HEAD
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
=======
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijian_jieguo);
        ButterKnife.bind(this);
>>>>>>> 9234417e158775ca1bb6653706e5b1c712ef6d6a
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
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> 9234417e158775ca1bb6653706e5b1c712ef6d6a
}
