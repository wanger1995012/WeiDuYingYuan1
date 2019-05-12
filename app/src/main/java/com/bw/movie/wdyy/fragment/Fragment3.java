package com.bw.movie.wdyy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.activity.LoginActivity;
import com.bw.movie.wdyy.activity.MyQianActivity;
import com.bw.movie.wdyy.activity.ShowActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:32
 * @Description：描述信息
 */
public class Fragment3 extends Fragment {
    @BindView(R.id.my_xiaoxi)
    ImageView myXiaoxi;
    @BindView(R.id.my_touxiang)
    SimpleDraweeView myTouxiang;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.myxin)
    ImageView myxin;
    @BindView(R.id.myguanzhi)
    ImageView myguanzhi;
    @BindView(R.id.myjilu)
    ImageView myjilu;
    @BindView(R.id.myyijian)
    ImageView myyijian;
    @BindView(R.id.myzuibanben)
    ImageView myzuibanben;
    @BindView(R.id.mytuichudeng)
    ImageView mytuichudeng;
    Unbinder unbinder;
    @BindView(R.id.my_qiandao)
    Button myQiandao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout3, null);
        //s
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置签到的点击事件
        QianInit();
        //设置退出登录
        TuichuInit();
        //设置我的信息
        XinxiIntit();
    }

    public void XinxiIntit() {
        
    }

    public void TuichuInit() {
        mytuichudeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void QianInit() {
        myQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyQianActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
