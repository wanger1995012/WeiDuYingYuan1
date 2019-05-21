package com.bw.movie.fragment.fragment1children;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.ShowListAdapter2;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.hotactivity.DetailsActivity;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/14 9:31
 * @Description：描述信息
 */
public class JiFragment extends Fragment implements ContractInterface.ViewMovieListChild2{
    RecyclerView recyclerView;
    List<ComingSoonBean.ResultBean> list = new ArrayList<>();
    private ShowListAdapter2 adapter;
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ji_children,null);
        recyclerView = view.findViewById(R.id.recycler_view_list_show2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ShowListAdapter2(list, getContext());
        recyclerView.setAdapter(adapter);
        adapter.setListener(new ShowListAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("MovieId",position+"");
                startActivity(intent);
            }
        });
        p.toModelChild2();

    }

    @Override
    public void ShowMovieList(ComingSoonBean bean) {
        List<ComingSoonBean.ResultBean> lists = bean.getResult();
        list.addAll(lists);
        adapter.notifyDataSetChanged();
    }
}
