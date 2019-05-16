package com.bw.movie.wdyy.hotactivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.FragmentListAdapter;
import com.bw.movie.wdyy.fragment.fragment1children.HotFragment;
import com.bw.movie.wdyy.fragment.fragment1children.JiFragment;
import com.bw.movie.wdyy.fragment.fragment1children.ReFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotActivity extends AppCompatActivity {

    ImageView imageFimlLoca;
    TextView textFimlLoca;
    ImageView image;
    EditText edFimlSe;
    TextView textFimlSe;
    RelativeLayout layoutFimlSe;
    int isShow = 1;
    RadioGroup group;
    ViewPager pager;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        initById();
        setShape();
        setViewPager();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setViewPager() {
        //创建集合，添加Fragment
        List<Fragment> list = new ArrayList<>();
        list.add(new HotFragment());
        list.add(new ReFragment());
        list.add(new JiFragment());
        FragmentListAdapter adapter = new FragmentListAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        pager.setCurrentItem(0,false);
                        break;
                    case R.id.rb2:
                        pager.setCurrentItem(1,false);
                        break;
                    case R.id.rb3:
                        pager.setCurrentItem(2,false);
                        break;
                }
            }
        });
    }

    private void setShape() {
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShow == 1){
                    textFimlSe.setVisibility(View.VISIBLE);
                    edFimlSe.setVisibility(View.VISIBLE);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",0,-290);
                    animator.setDuration(2000);
                    animator.start();
                    isShow=2;
                }
            }
        });

        //点击搜索缩进去
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow==2){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",-290,0);
                    animator.setDuration(2000);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    edFimlSe.setVisibility(View.GONE);
                    isShow=1;
                }
            }
        });
    }

    private void initById() {
        imageFimlLoca = findViewById(R.id.image_fiml_loca);
        textFimlLoca = findViewById(R.id.text_fiml_loca);
        image = findViewById(R.id.image_sou);
        edFimlSe = findViewById(R.id.ed_fiml_se);
        textFimlSe = findViewById(R.id.text_fiml_se);
        layoutFimlSe = findViewById(R.id.layout_fiml_se);
        back = findViewById(R.id.image_call_back);
        pager = findViewById(R.id.view_pager_list);
        group = findViewById(R.id.radio_group_re);
    }
}
