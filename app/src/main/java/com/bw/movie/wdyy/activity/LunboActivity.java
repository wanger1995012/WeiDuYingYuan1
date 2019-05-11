package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunboActivity extends AppCompatActivity {
    List<Integer> list = new ArrayList<>();
    @BindView(R.id.viewpager_id)
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbo);
        ButterKnife.bind(this);
        list.add(R.drawable.yindao1);
        list.add(R.drawable.yindao2);
        list.add(R.drawable.yindao3);
        list.add(R.drawable.yindao4);
        //设置适配器
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(list,LunboActivity.this);
        viewpager.setAdapter(myPagerAdapter);
        //监听
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==list.size()-1){
                    Intent intent=new Intent(LunboActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
