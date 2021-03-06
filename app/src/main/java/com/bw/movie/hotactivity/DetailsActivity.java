package com.bw.movie.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.adapter.CommentAdapter;
import com.bw.movie.adapter.DetailsAdapter;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements ContractInterface.DetailsShow, ContractInterface.FindAllMovieComment, XRecyclerView.LoadingListener {

    public RecyclerView recyclerView;
    CommentAdapter adapters ;
    List<DetailsBean.ResultBean> mList = new ArrayList<>();
    public ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    List<DetailsBean.ResultBean.ShortFilmListBean> list_bo = new ArrayList<>();
    List<FindAllMovieCommentBean.ResultBean> list_comment = new ArrayList<>();
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


    //第二个include
    @BindView(R.id.simple_draw_view2)
    public SimpleDraweeView spdv;
    @BindView(R.id.vedio_yincang)
    public ImageView vedioYincang;

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
    public RecyclerView rec;

    //影评
    @BindView(R.id.simple_draw_view4)
    public SimpleDraweeView ying_simpleDrawView4;
    @BindView(R.id.text_movie_name4)
    public TextView ying_textMovieName4;
    @BindView(R.id.image2_dianzan4)
    public ImageView ying_image2Dianzan4;
    @BindView(R.id.yingping_yincang)
    public ImageView ying_yingpingYincang;
    public XRecyclerView rec_yingping;
    @BindView(R.id.yingping_layout)
    public RelativeLayout ying_yingpingLayout;
    public int movieId;

    public ImageView clickPing;

    public EditText input_count;
    public TextView input_send;
    public RelativeLayout layout_input;
    private int commentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        layout_input = findViewById(R.id.layout_input);
        input_count = findViewById(R.id.input_ping);
        input_send  = findViewById(R.id.input_send);
        clickPing = findViewById(R.id.ping_input_img);
        recyclerView = findViewById(R.id.recycler_view_by_id);
        view = findViewById(R.id.parent_simple);
        rec_yingping =findViewById(R.id.recycler_yingping222);
        dianzan1 = findViewById(R.id.image2_dianzan2);
        dianzan2 = findViewById(R.id.image2_dianzan3);
        movie_name1 = findViewById(R.id.text_movie_name);
        movie_name2 = findViewById(R.id.text_movie_name_xiang);
        layout_xianshi = findViewById(R.id.layout_xianshi1);
        rec = findViewById(R.id.recycler_yugaoaaa);
        Intent intent = getIntent();
        String movieIds = intent.getStringExtra("MovieId");
        movieId = Integer.parseInt(movieIds);





        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //适配器
        adapter = new DetailsAdapter(mList, list_bo, list_comment, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rec_yingping.setLayoutManager(manager2);
        adapters = new CommentAdapter(list_comment, this);
        rec_yingping.setAdapter(adapters);
        rec_yingping.setLoadingMoreEnabled(true);
        rec_yingping.setPullRefreshEnabled(true);
        rec_yingping.setLoadingListener(this);
        p.toModelFindAllMovieComment(movieId, 1,10);
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
    public void setPing(String ping) {
        if(ping.equals("评论成功")){
            Toast.makeText(this, ping,Toast.LENGTH_LONG).show();
            list_comment.clear();
            p.toModelFindAllMovieComment(movieId, 1,10);
        }
    }

    @Override
    public void setYuanPiao(ScheduleBean yuanPiao) {

    }


    @Override
    public void setComment(FindAllMovieCommentBean comment) {
        rec_yingping.refreshComplete();
        rec_yingping.loadMoreComplete();
        Log.i("movieComment", "movieComment: " + comment);
        Log.i("movieComment", "movieComment: " + comment.getResult());
        this.list_comment.addAll(comment.getResult());
        commentId = comment.getResult().get(0).getCommentId();
        adapters.notifyDataSetChanged();
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


    @Override
    public void onRefresh() {
        list_comment.clear();
        p.toModelFindAllMovieComment(movieId, 1,10);
    }

    int page=1;
    @Override
    public void onLoadMore() {
        page++;
        p.toModelFindAllMovieComment(movieId, page,10);
    }
}
