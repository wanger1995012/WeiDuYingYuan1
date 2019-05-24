package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFoodedPayAdapter;
import com.bw.movie.bean.MyFoodedBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GouPiaoActivity extends AppCompatActivity {

    @BindView(R.id.goupiao_recycler)
    RecyclerView goupiao_Recycler;
    MyFoodedPayAdapter MyFoodedPayAdapte;
    List<MyFoodedBean.ResultBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_piao);
        ButterKnife.bind(this);
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goupiao_Recycler.setLayoutManager(layoutManager);
        //设置适配器
        MyFoodedPayAdapte=new MyFoodedPayAdapter(GouPiaoActivity.this,list);
        goupiao_Recycler.setAdapter(MyFoodedPayAdapte);
        //去P层

    }
}
