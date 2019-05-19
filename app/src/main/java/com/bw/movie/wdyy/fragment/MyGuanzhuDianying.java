package com.bw.movie.wdyy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.GZYYBean;
import com.bw.movie.wdyy.adapter.GuanZhuyingyuanAdapter;
import com.bw.movie.wdyy.adapter.GuanzhuDianyingAdapter;
import com.bw.movie.wdyy.bean.GZDYBean;
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
public class MyGuanzhuDianying extends Fragment implements ContractInterface.VGZyy {
    @BindView(R.id.myguanzhu_dianying)
    XRecyclerView myguanzhuDianying;
    Unbinder unbinder;
    int page=1;
    List<GZDYBean.ResultBean> list=new ArrayList<>();
    GuanzhuDianyingAdapter guanzhuDianyingAdapter;
    ContractInterface.PGZyy pgZyy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myguanzhudianying_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //设置管理器
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myguanzhuDianying.setLayoutManager(layoutManager);
        //设置上下拉
        myguanzhuDianying.setLoadingMoreEnabled(true);
        myguanzhuDianying.setPullRefreshEnabled(true);
        //设置适配器
        guanzhuDianyingAdapter=new GuanzhuDianyingAdapter(list,getActivity());
        myguanzhuDianying.setAdapter(guanzhuDianyingAdapter);
        //P
        pgZyy=new MyPresenter(this);
        pgZyy.PGZDY(1,10);
        //设置上下拉
        myguanzhuDianying.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                pgZyy.PGZDY(1,10);
            }

            @Override
            public void onLoadMore() {
                //设置下拉加载
                page++;
                pgZyy.PGZDY(page,10);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void VGZYY(List<GZYYBean.ResultBean> lst) {

    }

    @Override
    public void VGZDY(List<GZDYBean.ResultBean> lst) {
        myguanzhuDianying.loadMoreComplete();
        myguanzhuDianying.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        guanzhuDianyingAdapter.notifyDataSetChanged();
    }
}
