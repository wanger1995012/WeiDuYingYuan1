package com.bw.movie.wdyy.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.DetailsBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements ContractInterface.DetailsShow {

    @BindView(R.id.image2_dianzan)
    ImageView image2Dianzan;
    @BindView(R.id.text2_name)
    TextView text2Name;
    @BindView(R.id.text2_xiangqing)
    TextView text2Xiangqing;
    @BindView(R.id.text2_yugao)
    TextView text2Yugao;
    @BindView(R.id.text2_juzhao)
    TextView text2Juzhao;
    @BindView(R.id.text2_yingping)
    TextView text2Yingping;
    @BindView(R.id.image2_call)
    ImageView image2Call;
    @BindView(R.id.text2_goupiao)
    TextView text2Goupiao;
    List<DetailsBean.ResultBean> mList = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    @BindView(R.id.image2_big_img)
    ImageView image2BigImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("MovieId");
        int position = Integer.parseInt(movieId);
        image2Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        p.toModelQueryMovieInformation(position);
    }

    @Override
    public void MovieDetailsShow(List<DetailsBean.ResultBean> list) {
        mList.addAll(list);
        for (int i = 0; i <= list.size(); i++) {
            Glide.with(this).load(mList.get(i).getImageUrl()).into(image2BigImg);
            text2Name.setText(mList.get(i).getName());
        }
    }
}
