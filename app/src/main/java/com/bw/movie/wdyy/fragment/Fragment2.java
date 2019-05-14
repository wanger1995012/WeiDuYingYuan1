package com.bw.movie.wdyy.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.YingyuanFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:32
 * @Description：描述信息
 */
public class Fragment2 extends Fragment {


    int isShow = 1;
    @BindView(R.id.image_fiml_loca)
    ImageView imageFimlLoca;
    @BindView(R.id.text_fiml_loca)
    TextView textFimlLoca;
    @BindView(R.id.image_sou)
    ImageView imageSou;
    @BindView(R.id.ed_fiml_se)
    EditText edFimlSe;
    @BindView(R.id.text_fiml_se)
    TextView textFimlSe;
    @BindView(R.id.layout_fiml_se)
    RelativeLayout layoutFimlSe;
    Unbinder unbinder;
    @BindView(R.id.yingyuan_tablayout)
    TabLayout yingyuanTablayout;
    @BindView(R.id.yingyuan_viewpager)
    ViewPager yingyuanViewpager;

    //设置list集合
    List<Fragment> list=new ArrayList<>();
    String[] titles={"推荐影院","附近影院"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout2, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow == 1) {
                    textFimlSe.setVisibility(View.VISIBLE);
                    edFimlSe.setVisibility(View.VISIBLE);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", 0, -200);
                    animator.setDuration(2000);
                    animator.start();
                    isShow = 2;
                }
            }
        });

        //点击搜索缩进去
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow == 2) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", -200, 0);
                    animator.setDuration(2000);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    edFimlSe.setVisibility(View.GONE);
                    isShow = 1;
                }
            }
        });
        //设置viewpager+tablayout
        Viewpainit();
    }

    private void Viewpainit() {
        list.add(new YingFragment1());
        list.add(new YingFragment2());
        //设置适配器
        YingyuanFragmentPagerAdapter yingyuanFragmentPagerAdapter=new YingyuanFragmentPagerAdapter(getChildFragmentManager(),list,titles);
        yingyuanViewpager.setAdapter(yingyuanFragmentPagerAdapter);
        yingyuanTablayout.setupWithViewPager(yingyuanViewpager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
