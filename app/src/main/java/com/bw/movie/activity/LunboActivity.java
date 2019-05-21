package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyPagerAdapter;
import com.bw.movie.fragment.lunbo.LunboFragment1;
import com.bw.movie.fragment.lunbo.LunboFragment2;
import com.bw.movie.fragment.lunbo.LunboFragment3;
import com.bw.movie.fragment.lunbo.LunboFragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunboActivity extends AppCompatActivity {
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.viewpager_id)
    ViewPager viewpager;
    @BindView(R.id.lunbo_radio1)
    RadioButton lunboRadio1;
    @BindView(R.id.lunbo_radio2)
    RadioButton lunboRadio2;
    @BindView(R.id.lunbo_radio3)
    RadioButton lunboRadio3;
    @BindView(R.id.lunbo_radio4)
    RadioButton lunboRadio4;
    @BindView(R.id.lunbo_radio)
    RadioGroup lunboRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbo);
        ButterKnife.bind(this);
        list.add(new LunboFragment1());
        list.add(new LunboFragment2());
        list.add(new LunboFragment3());
        list.add(new LunboFragment4());
        //设置适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(myPagerAdapter);
        //监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                lunboRadio.check(lunboRadio.getChildAt(i).getId());
                if (i==list.size()-1){
                    Intent intent=new Intent(LunboActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置圆点的监听
        lunboRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.lunbo_radio1:
                        viewpager.setCurrentItem(0,false);
                        break;
                    case R.id.lunbo_radio2:
                        viewpager.setCurrentItem(1,false);
                        break;
                    case R.id.lunbo_radio3:
                        viewpager.setCurrentItem(2,false);
                        break;
                    case R.id.lunbo_radio4:
                        viewpager.setCurrentItem(3,false);
                        break;

                }
            }
        });
    }
}
