package com.bw.movie.wdyy.fragment;

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
public class MyGuanzhuYingyuan extends Fragment implements ContractInterface.VGZyy {
    @BindView(R.id.myguanzhu_yingyuan)
    XRecyclerView myguanzhuYingyuan;
    Unbinder unbinder;
    GuanZhuyingyuanAdapter guanZhuyingyuanAdapter;
    ContractInterface.PGZyy pgZyy;
    List<GZYYBean.ResultBean> list=new ArrayList<>();
    int page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myguanzhuyingyuan_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myguanzhuYingyuan.setLayoutManager(layoutManager);
        //设置上下拉
        myguanzhuYingyuan.setLoadingMoreEnabled(true);
        myguanzhuYingyuan.setPullRefreshEnabled(true);
        //设置适配器
        guanZhuyingyuanAdapter=new GuanZhuyingyuanAdapter(list,getActivity());
        myguanzhuYingyuan.setAdapter(guanZhuyingyuanAdapter);
        //P
        pgZyy=new MyPresenter(this);
        //设置上下拉
        myguanzhuYingyuan.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                pgZyy.PGZYY(page,10);
            }

            @Override
            public void onLoadMore() {
                //设置下拉加载
                page++;
                pgZyy.PGZYY(page,10);
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
        myguanzhuYingyuan.loadMoreComplete();
        myguanzhuYingyuan.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        guanZhuyingyuanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pgZyy.onDestory();
        pgZyy=null;
    }
}
