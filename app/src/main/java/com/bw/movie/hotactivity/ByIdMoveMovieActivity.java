package com.bw.movie.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;


public class ByIdMoveMovieActivity extends AppCompatActivity implements ContractInterface.ViewFindYuan {


    TextView yuanMovieName;
    RecyclerView xRecyclerViewYuan;
    private int movieId;
    ImageView img_back;
    public List<CinemaBean.ResultBean> mList = new ArrayList<>();
    private CinemaAdapter adapter;
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    private String names;
    private String movieIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_id_move_movie);
        yuanMovieName = findViewById(R.id.yuan_movie_name);
        xRecyclerViewYuan = findViewById(R.id.x_recycler_view_yuan);
        img_back = findViewById(R.id.yuan_back);
        final Intent intent = getIntent();
        String movieIds = intent.getStringExtra("movieId");
        names = intent.getStringExtra("name");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        yuanMovieName.setText(names);
        movieId = Integer.parseInt(movieIds);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerViewYuan.setLayoutManager(manager);
        //适配器
        adapter = new CinemaAdapter(mList, this);
        adapter.setMyCallBack(new CinemaAdapter.MyCallBack() {
            @Override
            public void clickListener(View view, String address,String name, int id) {
                Intent intent1 = new Intent(ByIdMoveMovieActivity.this,TimeListActivity.class);
                intent1.putExtra("m_name",names+"");
                intent1.putExtra("movieId",movieId+"");
                intent1.putExtra("y_name",name+"");
                intent1.putExtra("y_id",id+"");
                intent1.putExtra("y_address",address+"");
                startActivity(intent1);
            }
        });
        xRecyclerViewYuan.setAdapter(adapter);
        p.toModelFindYuan(movieId);
    }

    @Override
    public void findYuan(CinemaBean cinemaBean) {
        List<CinemaBean.ResultBean> list = cinemaBean.getResult();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
