package com.bw.movie.wdyy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.HotAdapter;
import com.bw.movie.wdyy.adapter.JiAdapter;
import com.bw.movie.wdyy.adapter.MyBigAdapter;
import com.bw.movie.wdyy.adapter.ReAdapter;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:32
 * @Description：描述信息
 */
public class Fragment1 extends Fragment implements ContractInterface.ViewMovieList {
    List<HotMovieListBean.ResultBean> bean1 = new ArrayList<>();
    List<NowPlayingBean.ResultBean>   bean2 = new ArrayList<>();
    List<ComingSoonBean.ResultBean>   bean3 = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    RecyclerCoverFlow flow;
    RecyclerView rc1,rc2,rc3;
    private HotAdapter adapter1;
    private ReAdapter adapter2;
    private JiAdapter adapter3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout1,null);
        flow= view.findViewById(R.id.recycler_cover_flow);
        rc1 = view.findViewById(R.id.recycler_view_hot_movie);
        rc2 = view.findViewById(R.id.recycler_view_re_movie);
        rc3 = view.findViewById(R.id.recycler_view_ji_movie);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRc1();
        setRc2();
        setRc3();
        p.toModel1();
        p.toModel2();
        p.toModel3();
    }

    private void setRc3() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc3.setLayoutManager(manager);
        //创建适配器
        adapter3 = new JiAdapter(bean3, getContext());
        rc3.setAdapter(adapter3);
    }

    private void setRc2() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc2.setLayoutManager(manager);
        //创建适配器
        adapter2 = new ReAdapter(bean2, getContext());
        rc2.setAdapter(adapter2);
    }

    private void setRc1() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc1.setLayoutManager(manager);
        //创建适配器
        adapter1 = new HotAdapter(bean1, getContext());
        rc1.setAdapter(adapter1);
    }

    @Override
    public void ShowMovieList1(HotMovieListBean bean) {
        List<HotMovieListBean.ResultBean> list = bean.getResult();
        bean1.addAll(list);
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void ShowMovieList2(NowPlayingBean bean) {
        List<NowPlayingBean.ResultBean> list = bean.getResult();
        bean2.addAll(list);
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void ShowMovieList3(ComingSoonBean bean) {
        List<ComingSoonBean.ResultBean> list = bean.getResult();
        bean3.addAll(list);
        adapter3.notifyDataSetChanged();
    }
}
