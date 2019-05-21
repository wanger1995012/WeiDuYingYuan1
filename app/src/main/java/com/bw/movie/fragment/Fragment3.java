package com.bw.movie.fragment;

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
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MyGuanzhuActivity;
import com.bw.movie.activity.MyQianActivity;
import com.bw.movie.activity.XiaoxiActivity;
import com.bw.movie.activity.XinXiActivity;
import com.bw.movie.activity.YiJianActivity;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
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
public class Fragment3 extends Fragment implements ContractInterface.VYiJian {
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
    ContractInterface.PLogin pLogin;
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
        pLogin=new MyPresenter(this);
        //设置签到的点击事件
        QianInit();
        //设置退出登录
        TuichuInit();
        //设置我的信息
        XinxiIntit();
        //设置意见反馈页
        YiJianInit();
        //设置最新版本
        BanBenInit();
        //设置我的关注
        MyGuanzhiInit();
        //设置系统消息
        XiaoxinInit();
        //购票记录
        GoupiaojiluInit();
    }
    //购票记录
    private void GoupiaojiluInit() {
        myjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //设置系统消息
    private void XiaoxinInit() {
        myXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),XiaoxiActivity.class);
                startActivity(intent);
            }
        });
    }

    //我的关注
    private void MyGuanzhiInit() {
        myguanzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyGuanzhuActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    //最新版本
    private void BanBenInit() {
        myzuibanben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pLogin.PBanben();
            }
        });
    }

    //意见反馈
    private void YiJianInit() {
        myyijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),YiJianActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    //我的信息
    public void XinxiIntit() {
        myxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),XinXiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    //退出登录
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
    //签到
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

    @Override
    public void VYijian(String str) {

    }

    @Override
    public void VBanben(String flag) {
        if (flag.equals("1")){
            Toast.makeText(getContext(),"有新版本，需要更新",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(),"没新版本，不需要更新",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pLogin.onDestory();
        pLogin=null;
    }
}
