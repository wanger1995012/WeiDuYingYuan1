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
import android.widget.VideoView;

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
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class DetailsActivity extends AppCompatActivity implements ContractInterface.DetailsShow {

    public RecyclerView recyclerView;
    List<DetailsBean.ResultBean> mList = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    List<DetailsBean.ResultBean.ShortFilmListBean> list_bo = new ArrayList<>();
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
    @BindView(R.id.shiyan)
    public TextView shiyan;

    public RecyclerView recycler_video_player;

    //第二个include
    @BindView(R.id.simple_draw_view2)
    public SimpleDraweeView spdv;
    @BindView(R.id.vedio_yincang)
    public ImageView vedioYincang;
    @BindView(R.id.video_view1)
    public JCVideoPlayerStandard videoView1;

    //剧照
    @BindView(R.id.layout_xianshi1)
    public RelativeLayout layoutXianshi1;
    @BindView(R.id.simple_draw_view3)
    public SimpleDraweeView ju_simpleDrawView3;
    @BindView(R.id.text_movie_name3)
    public TextView ju_textMovieName3;
    @BindView(R.id.image2_dianzan3)
    public ImageView ju_image2Dianzan3;
    @BindView(R.id.juzhao_yin_cang)
    public ImageView ju_juzhaoYinCang;
    @BindView(R.id.image_left_ju_zhao1)
    public SimpleDraweeView ju_imageLeftJuZhao1;
    @BindView(R.id.image_left_ju_zhao2)
    public SimpleDraweeView ju_imageLeftJuZhao2;
    @BindView(R.id.image_left_ju_zhao3)
    public SimpleDraweeView ju_imageLeftJuZhao3;
    @BindView(R.id.image_right_ju_zhao1)
    public SimpleDraweeView ju_imageRightJuZhao1;
    @BindView(R.id.image_right_ju_zhao2)
    public SimpleDraweeView ju_imageRightJuZhao2;
    @BindView(R.id.image_right_ju_zhao3)
    public SimpleDraweeView ju_imageRightJuZhao3;
    @BindView(R.id.layout_xianshi_juzhao)
    public RelativeLayout ju_layoutXianshiJuzhao;
    private DetailsAdapter adapter;
    public RelativeLayout layout_xianshi;
    public ImageView dianzan1, dianzan2;
    public TextView movie_name1, movie_name2;
    public SimpleDraweeView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler_view_by_id);
        recycler_video_player = findViewById(R.id.recycler_video_player);
        view = findViewById(R.id.parent_simple);
        dianzan1 = findViewById(R.id.image2_dianzan2);
        dianzan2 = findViewById(R.id.image2_dianzan3);
        movie_name1 = findViewById(R.id.text_movie_name);
        movie_name2 = findViewById(R.id.text_movie_name_xiang);
        layout_xianshi = findViewById(R.id.layout_xianshi1);
        Intent intent = getIntent();
        String movieIds = intent.getStringExtra("MovieId");
        int movieId = Integer.parseInt(movieIds);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //适配器
        adapter = new DetailsAdapter(mList,list_bo,this);
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
