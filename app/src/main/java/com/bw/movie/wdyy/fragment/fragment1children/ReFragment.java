package com.bw.movie.wdyy.fragment.fragment1children;

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
import com.bw.movie.wdyy.adapter.ShowListAdapter;
import com.bw.movie.wdyy.adapter.ShowListAdapter3;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/14 9:31
 * @Description：描述信息
 */
public class ReFragment extends Fragment implements ContractInterface.ViewMovieListChild3 {
    RecyclerView recyclerView;
    List<NowPlayingBean.ResultBean> list = new ArrayList<>();
    private ShowListAdapter3 adapter;
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.re_children,null);
        recyclerView = view.findViewById(R.id.recycler_view_list_show3);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ShowListAdapter3(list, getContext());
        recyclerView.setAdapter(adapter);
        p.toModelChild3();
    }

    @Override
    public void ShowMovieList(NowPlayingBean bean) {
        List<NowPlayingBean.ResultBean> lists = bean.getResult();
        list.addAll(lists);
        adapter.notifyDataSetChanged();
    }
}
