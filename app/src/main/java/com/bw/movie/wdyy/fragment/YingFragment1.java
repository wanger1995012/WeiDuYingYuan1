package com.bw.movie.wdyy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class YingFragment1 extends Fragment implements ContractInterface.VYingyuan {
    Unbinder unbinder;
    List<TuijianBean.ResultBean> list = new ArrayList<>();
    TuijianAdapter tuijianAdapter;
    ContractInterface.PYingyuan pYingyuan;
    @BindView(R.id.yingyuan_tuijianrecyclerview)
    XRecyclerView yingyuanTuijianrecyclerview;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yingyuan_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yingyuanTuijianrecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        tuijianAdapter = new TuijianAdapter(list, getActivity());
        yingyuanTuijianrecyclerview.setAdapter(tuijianAdapter);
        //设置P层
        pYingyuan = new MyPresenter(this);
        pYingyuan.PTuijian(1,10);
         //设置上下拉
        yingyuanTuijianrecyclerview.setPullRefreshEnabled(true);
        yingyuanTuijianrecyclerview.setLoadingMoreEnabled(true);
        //设置上下拉的监听
        yingyuanTuijianrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                pYingyuan.PTuijian(page,10);
            }

            @Override
            public void onLoadMore() {
                //上拉加载
                page++;
                pYingyuan.PTuijian(page,10);
            }
        });
        tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
            @Override
            public void weiGuanzhu(String str) {
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void QvxiaoGuanzhu(String str) {
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
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
        yingyuanTuijianrecyclerview.loadMoreComplete();
        yingyuanTuijianrecyclerview.refreshComplete();
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
