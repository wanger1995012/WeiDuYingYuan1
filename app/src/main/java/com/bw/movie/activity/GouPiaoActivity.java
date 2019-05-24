package com.bw.movie.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFoodedPayAdapter;
import com.bw.movie.bean.MyFoodedBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GouPiaoActivity extends AppCompatActivity implements ContractInterface.VGPJL {

    @BindView(R.id.goupiao_recycler)
    XRecyclerView goupiao_Recycler;
    @BindView(R.id.guopiao_yifu)
    Button goupiao_yifu;
    MyFoodedPayAdapter MyFoodedPayAdapte;
    List<MyFoodedBean.ResultBean> list = new ArrayList<>();
    ContractInterface.PGPJL pgpjl;
    int page = 1;
    @BindView(R.id.guopiao_weifukuan)
    Button guopiaoWeifukuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_piao);
        ButterKnife.bind(this);
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goupiao_Recycler.setLayoutManager(layoutManager);
        //开启上下拉
        goupiao_Recycler.setPullRefreshEnabled(true);
        goupiao_Recycler.setLoadingMoreEnabled(true);
        //设置适配器
        MyFoodedPayAdapte = new MyFoodedPayAdapter(GouPiaoActivity.this, list);
        goupiao_Recycler.setAdapter(MyFoodedPayAdapte);
        //去P层
        pgpjl = new MyPresenter(this);
        pgpjl.VGPJL(1, 5, 1);
        //设置上下拉
        goupiao_Recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                pgpjl.VGPJL(1, 5, 1);
            }

            @Override
            public void onLoadMore() {
                //加载
                page++;
                pgpjl.VGPJL(page, 5, 1);
            }
        });
        //设置点击事件
        weifunInit();
        fukuanInit();
    }

    //付款
    private void fukuanInit() {
        goupiao_yifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                //改变背景颜色
                goupiao_yifu.setBackground(getResources().getDrawable(R.drawable.shap1));
                guopiaoWeifukuan.setBackground(getResources().getDrawable(R.drawable.shape1));
                guopiaoWeifukuan.setTextColor(Color.BLACK);
                goupiao_yifu.setTextColor(Color.WHITE);

                pgpjl.VGPJL(1, 5, 2);
                //设置上下拉
                goupiao_Recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //刷新
                        pgpjl.VGPJL(1, 5, 2);
                    }

                    @Override
                    public void onLoadMore() {
                        //加载
                        page++;
                        pgpjl.VGPJL(page, 5, 2);
                    }
                });

            }
        });
    }

    //未付款
    private void weifunInit() {
        guopiaoWeifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                //改变背景颜色
                goupiao_yifu.setBackground(getResources().getDrawable(R.drawable.shape1));
                guopiaoWeifukuan.setBackground(getResources().getDrawable(R.drawable.shap1));
                goupiao_yifu.setTextColor(Color.BLACK);
                guopiaoWeifukuan.setTextColor(Color.WHITE);

                pgpjl.VGPJL(1, 5, 1);
                //设置上下拉
                goupiao_Recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //刷新
                        pgpjl.VGPJL(1, 5, 1);
                    }

                    @Override
                    public void onLoadMore() {
                        //加载
                        page++;
                        pgpjl.VGPJL(page, 5, 1);
                    }
                });
            }
        });
    }

    @Override
    public void VGPJL(List<MyFoodedBean.ResultBean> lst) {
        list.clear();
        goupiao_Recycler.refreshComplete();
        goupiao_Recycler.loadMoreComplete();
        this.list.addAll(lst);
        MyFoodedPayAdapte.notifyDataSetChanged();
    }
}
