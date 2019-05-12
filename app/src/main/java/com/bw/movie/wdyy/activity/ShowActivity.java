package com.bw.movie.wdyy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.MyFragmentPagerAdapter;
import com.bw.movie.wdyy.fragment.Fragment1;
import com.bw.movie.wdyy.fragment.Fragment2;
import com.bw.movie.wdyy.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_show)
    ViewPager viewpagerShow;
    @BindView(R.id.radio_btn1)
    RadioButton radioBtn1;
    @BindView(R.id.radio_btn2)
    RadioButton radioBtn2;
    @BindView(R.id.radio_btn3)
    RadioButton radioBtn3;
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.radio)
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        //添加fragment到集合中
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        //设置适配器
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);
        viewpagerShow.setAdapter(myFragmentPagerAdapter);
        //设置viewpager默认显示第一个
        viewpagerShow.setCurrentItem(0);
        //设置viewpager的监听
        viewpagerShow.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                radio.check(radio.getChildAt(i).getId());
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置radiogroup的监听
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_btn1:
                        viewpagerShow.setCurrentItem(0,false);
                        break;
                    case R.id.radio_btn2:
                        viewpagerShow.setCurrentItem(1,false);
                        break;
                    case R.id.radio_btn3:
                        viewpagerShow.setCurrentItem(2,false);
                        break;

                }
            }
        });
    }
}
