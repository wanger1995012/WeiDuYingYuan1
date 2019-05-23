package com.bw.movie.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.ScheduleAdapter;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeListActivity extends AppCompatActivity implements ContractInterface.DetailsShow {
    List<ScheduleBean.ResultBean> list = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    @BindView(R.id.ying_yuan_name)
    TextView yingYuanName;
    @BindView(R.id.ying_yuan_address)
    TextView yingYuanAddress;
    @BindView(R.id.ying_yuan_ding_wei)
    ImageView yingYuanDingWei;
    @BindView(R.id.ying_movie_name)
    TextView yingMovieName;
    @BindView(R.id.ying_type)
    TextView yingType;
    @BindView(R.id.ying_daoyan)
    TextView yingDaoyan;
    @BindView(R.id.ying_time)
    TextView yingTime;
    @BindView(R.id.ying_address)
    TextView yingAddress;
    private int movieId;
    SimpleDraweeView ying_image;
    RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private String y_id;
    ImageView piao_back;
    private String y_name;
    private String y_address;
    private String m_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);
        ButterKnife.bind(this);
        piao_back = findViewById(R.id.piao_back);
        recyclerView = findViewById(R.id.recycle_piao);
        ying_image = findViewById(R.id.ying_image);
        final Intent intent = getIntent();
        m_name = intent.getStringExtra("m_name");
        String movieIds = intent.getStringExtra("movieId");
        movieId = Integer.parseInt(movieIds);
        y_name = intent.getStringExtra("y_name");
        y_id = intent.getStringExtra("y_id");
        y_address = intent.getStringExtra("y_address");
        yingYuanName.setText(y_name);
        yingYuanAddress.setText(y_address);
        piao_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager manager =  new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ScheduleAdapter(list, this);
        recyclerView.setAdapter(adapter);
        adapter.setMyCallBack(new ScheduleAdapter.MyCallBack() {
            @Override
            public void clickListener(View view, String begin, String end, String ting , double price,String id) {
                Intent intent1 = new Intent(TimeListActivity.this, XuanZuoActivity.class);
                intent1.putExtra("begin",begin);
                intent1.putExtra("end",end);
                intent1.putExtra("ting",ting);
                intent1.putExtra("y_name", y_name);
                intent1.putExtra("y_address",y_address);
                intent1.putExtra("m_name", m_name);
                intent1.putExtra("price" , price+"");
                intent1.putExtra("id" , id+"");
                startActivity(intent1);
            }
        });

        p.toModelQueryMovieInformation(movieId);
        p.toModelFindSchedule(y_id, movieId);
    }

    @Override
    public void MovieDetailsShow(Object bean) {
        DetailsBean bean2 = (DetailsBean) bean;
        Log.i("tags", "bean2   === : " + bean2.toString());
        yingMovieName.setText(bean2.getResult().getName());
        ying_image.setImageURI(bean2.getResult().getImageUrl());
        yingType.setText("类型：" + bean2.getResult().getMovieTypes());
        yingTime.setText("时长："+bean2.getResult().getDuration());
        yingDaoyan.setText("导演："+ bean2.getResult().getDirector());
        yingAddress.setText("产地："+bean2.getResult().getPlaceOrigin());
        /**
         * xiangType.setText("类型："+list.get(i).getMovieTypes());
         * xiangAddress.setText("产地："+list.get(i).getPlaceOrigin());
         * xiangDaoyan.setText("导演："+ list.get(i).getDirector());
         * xiangTime.setText("时长："+list.get(i).getDuration());
         */
    }

    @Override
    public void setPing(String ping) {

    }

    @Override
    public void setYuanPiao(ScheduleBean yuanPiao) {
        List<ScheduleBean.ResultBean> lists = yuanPiao.getResult();
        list.addAll(lists);
        adapter.notifyDataSetChanged();
    }
}
