package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.bw.movie.R;
import com.bw.movie.adapter.MyGuanzhuFragmentPagerAdapter;
import com.bw.movie.fragment.MyGuanzhuDianying;
import com.bw.movie.fragment.MyGuanzhuYingyuan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyGuanzhuActivity extends AppCompatActivity {

    @BindView(R.id.myguanzhu_radio1)
    RadioButton myguanzhuRadio1;
    @BindView(R.id.myguanzhu_radio2)
    RadioButton myguanzhuRadio2;
    @BindView(R.id.myguanzhu_radio)
    RadioGroup myguanzhuRadio;
    @BindView(R.id.myguanzhu_viewpager)
    ViewPager myguanzhuViewpager;

    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.myguanzhu_fanhui)
    ImageView myguanzhuFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guanzhu);
        ButterKnife.bind(this);
        //设置集合
        list.add(new MyGuanzhuDianying());
        list.add(new MyGuanzhuYingyuan());
        //设置适配器
        MyGuanzhuFragmentPagerAdapter myGuanzhuFragmentPagerAdapter = new MyGuanzhuFragmentPagerAdapter(getSupportFragmentManager(), list);
        myguanzhuViewpager.setAdapter(myGuanzhuFragmentPagerAdapter);
        //设置监听
        //设置viewpager默认显示第1个
        myguanzhuViewpager.setCurrentItem(0);
        //设置viewpager的监听
        myguanzhuViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                myguanzhuRadio.check(myguanzhuRadio.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置radiogroup的监听
        myguanzhuRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.myguanzhu_radio1:
                        myguanzhuViewpager.setCurrentItem(0, false);
                        break;
                    case R.id.myguanzhu_radio2:
                        myguanzhuViewpager.setCurrentItem(1, false);
                        break;
                }
            }
        });
        //设置返回的点击事件
        myguanzhuFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyGuanzhuActivity.this,ShowActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
                finish();
            }
        });
    }
}
