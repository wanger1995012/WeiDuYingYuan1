package com.bw.movie.wdyy.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.DetailsAdapter;
import com.bw.movie.wdyy.bean.DetailsBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements ContractInterface.DetailsShow {

    public RecyclerView recyclerView;
    List<DetailsBean.ResultBean> mList = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    @BindView(R.id.recycler_view_by_id)
    public RecyclerView recyclerViewById;
    @BindView(R.id.xiang_movie_name)
    public TextView xiangMovieName;
    @BindView(R.id.xiang_type)
    public TextView xiangType;
    @BindView(R.id.xiang_daoyan)
    public TextView xiangDaoyan;
    @BindView(R.id.xiang_time)
    public TextView xiangTime;
    @BindView(R.id.xiang_address)
    public TextView xiangAddress;
    @BindView(R.id.xiang_xiang)
    public TextView xiangXiang;
    @BindView(R.id.xiang_movie_image)
    public ImageView xiangMovieImage;
    @BindView(R.id.layout_xianshi)
    public RelativeLayout layoutXianshi;
    @BindView(R.id.relative_layout_movie_img)
    public RelativeLayout relativeLayoutMovieImg;
    @BindView(R.id.xiang_yincang)
    public ImageView xiangYincang;
    @BindView(R.id.simple_draw_view)
    public SimpleDraweeView simpleDrawView;
    private DetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler_view_by_id);
        Intent intent = getIntent();
        String movieIds = intent.getStringExtra("MovieId");
        int movieId = Integer.parseInt(movieIds);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //适配器
        adapter = new DetailsAdapter(mList, this);
        recyclerView.setAdapter(adapter);

        p.toModelQueryMovieInformation(movieId);
    }

    @Override
    public void MovieDetailsShow(Object o) {
        Log.i("tags", "MovieDetailsShow: " + o);
        DetailsBean bean = (DetailsBean) o;
        this.mList.add(bean.getResult());
        adapter.notifyDataSetChanged();
        Log.i("tags", "MovieDetailsShow: " + mList.get(0).getImageUrl());
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
