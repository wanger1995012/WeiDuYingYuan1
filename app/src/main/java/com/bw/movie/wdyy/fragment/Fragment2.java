package com.bw.movie.wdyy.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.TuijianAdapter;
import com.bw.movie.wdyy.bean.TuijianBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
public class Fragment2 extends Fragment implements ContractInterface.VYingyuan {


    int isShow = 1;
    @BindView(R.id.image_fiml_loca)
    ImageView imageFimlLoca;
    @BindView(R.id.text_fiml_loca)
    TextView textFimlLoca;
    @BindView(R.id.image_sou)
    ImageView imageSou;
    @BindView(R.id.yingyuan_edit)
    EditText yingyuan_edit;
    @BindView(R.id.text_fiml_se)
    TextView textFimlSe;
    @BindView(R.id.layout_fiml_se)
    RelativeLayout layoutFimlSe;
    Unbinder unbinder;

    @BindView(R.id.yingyuan_fujin)
    Button yingyuanFujin;
    @BindView(R.id.yingyuan_viewpager)
    XRecyclerView yingyuanrecycler;

    //设置list集合
    int page = 1;
    List<TuijianBean.ResultBean> list = new ArrayList<>();
    TuijianAdapter tuijianAdapter;
    ContractInterface.PYingyuan pYingyuan;
    @BindView(R.id.yingyuan_tuijian)
    Button yingyuanTuijian;
    @BindView(R.id.yingyuan_radio)
    LinearLayout yingyuanRadio;
    Unbinder unbinder1;
    int YYID;
    int I;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout2, null);
        unbinder1 = ButterKnife.bind(this, view);
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
                    yingyuan_edit.setVisibility(View.VISIBLE);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", 0, -290);
                    animator.setDuration(1500);
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
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", -290, 0);
                    animator.setDuration(1500);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    yingyuan_edit.setVisibility(View.GONE);
                    isShow = 1;
                }
            }
        });
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yingyuanrecycler.setLayoutManager(layoutManager);
        //设置P层
        pYingyuan = new MyPresenter(this);
        pYingyuan.PTuijian(1, 10);
        //设置推荐影院的点击搜索
        TuijianCacli();
        //设置附近影院的点击搜索
        FujinCacli();
        //设置影院的搜索框
        MoHucaxun();
    }

    private void MoHucaxun() {
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanTuijian.setTextColor(Color.BLACK);
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanFujin.setTextColor(Color.BLACK);
                //获取搜索框的内容
                String cinemaName = yingyuan_edit.getText().toString();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PYYMhucaxun(cinemaName,1, 10);

                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }

    //设置附近影院的点击搜索
    private void FujinCacli() {

        yingyuanFujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置按钮的点击监听
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.shap1));
                yingyuanFujin.setTextColor(Color.WHITE);
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanTuijian.setTextColor(Color.BLACK);
                list.clear();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PTuijian(1, 10);
                //设置上下拉的监听
                yingyuanrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷新
                        pYingyuan.PFujin("", "", 1, 10);
                    }

                    @Override
                    public void onLoadMore() {
                        //上拉加载
                        page++;
                        pYingyuan.PFujin("", "", page, 10);
                    }
                });
                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //设置推荐影院的点击搜索
    private void TuijianCacli() {
        yingyuanTuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置按钮的点击监听
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.shap1));
                yingyuanTuijian.setTextColor(Color.WHITE);
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanFujin.setTextColor(Color.BLACK);
                list.clear();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PTuijian(1, 10);
                //设置上下拉的监听
                yingyuanrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷新
                        pYingyuan.PTuijian(1, 10);
                    }

                    @Override
                    public void onLoadMore() {
                        //上拉加载
                        page++;
                        pYingyuan.PTuijian(page, 10);
                    }
                });
                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void VTuijian(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void VFujin(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void VYYMohuca(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pYingyuan.onDestory();
        pYingyuan = null;
    }
}
