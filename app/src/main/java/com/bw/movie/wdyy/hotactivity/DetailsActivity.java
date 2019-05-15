package com.bw.movie.wdyy.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.DetailsAdapter;
import com.bw.movie.wdyy.bean.DetailsBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements ContractInterface.DetailsShow {

    RecyclerView recyclerView;
    List<DetailsBean.ResultBean> mList = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    private DetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recyclerView = findViewById(R.id.recycler_view_by_id);
        Intent intent = getIntent();
        String movieIds = intent.getStringExtra("MovieId");
        int movieId = Integer.parseInt(movieIds);


        LinearLayoutManager manager  = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //适配器
        adapter = new DetailsAdapter(mList, this);
        recyclerView.setAdapter(adapter);

        p.toModelQueryMovieInformation(movieId);
    }

    @Override
    public void MovieDetailsShow(DetailsBean o) {
        Log.i("tags", "MovieDetailsShow的值为: " +o .toString());
        List<DetailsBean.ResultBean> lists = (List<DetailsBean.ResultBean>) o.getResult();
        mList.addAll(lists);
        adapter.notifyDataSetChanged();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p = null;
            mList.clear();
            mList = null;

        }
    }
}
