package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyTongzhiAdapter;
import com.bw.movie.bean.TongzhiBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:今夕何夕
 * 时间:2019/5/15 0015
 * Description:这个是系统通知
 */
public class XiaoxiActivity extends AppCompatActivity implements ContractInterface.VXTTZ {

    @BindView(R.id.tongzhi_weiduxiaoxi)
    TextView tongzhiWeiduxiaoxi;
    @BindView(R.id.tongzhi_fanhui)
    ImageView tongzhiFanhui;
    //适配
    MyTongzhiAdapter myTongzhiAdapter;
    List<TongzhiBean.ResultBean> list = new ArrayList<>();
    @BindView(R.id.tongzhi_recyclerview)
    XRecyclerView tongzhiRecyclerview;
    ContractInterface.PXTTZ pxttz;
    int page=1;
    //获取消息的id
    int xiaoxiId;
    //得到未读消息的总数
    int weiduxiaoxi=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);
        ButterKnife.bind(this);
        //去P层
        pxttz=new MyPresenter(this);
        //设置返回
        FanhuiInit();
        //设置通知
        tongzhiRInit();
    }

    //recyclerview
    private void tongzhiRInit() {
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tongzhiRecyclerview.setLayoutManager(layoutManager);
        //设置上下拉
        tongzhiRecyclerview.setPullRefreshEnabled(true);
        tongzhiRecyclerview.setLoadingMoreEnabled(true);
        //设置适配器
        myTongzhiAdapter = new MyTongzhiAdapter(list, XiaoxiActivity.this);
        tongzhiRecyclerview.setAdapter(myTongzhiAdapter);
        //得到未读消息总数
        myTongzhiAdapter.setMycall(new MyTongzhiAdapter.Mycall() {
            @Override
            public void Tongzhixiaox(int aa) {
                weiduxiaoxi=0;
                weiduxiaoxi=aa;
                tongzhiWeiduxiaoxi.setText(weiduxiaoxi + "");
            }
        });
        //得到消息的id
        myTongzhiAdapter.setMyCallXiaoxi(new MyTongzhiAdapter.MyCallXiaoxi() {
            @Override
            public void xiaoxiid(List<TongzhiBean.ResultBean> lst,int i) {
                xiaoxiId=lst.get(i).getId();
                pxttz.PXTTZXXID(xiaoxiId);
            }
        });
        pxttz.PXTTZ(1,5);
        //设置上下拉
        tongzhiRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                pxttz.PXTTZ(1,5);
            }

            @Override
            public void onLoadMore() {
                //下拉刷新
                page++;
                pxttz.PXTTZ(page,5);
            }
        });
    }

    //返回
    private void FanhuiInit() {
        tongzhiFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void VXTTZ(List<TongzhiBean.ResultBean> lst) {
        tongzhiRecyclerview.loadMoreComplete();
        tongzhiRecyclerview.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        myTongzhiAdapter.notifyDataSetChanged();
    }

    @Override
    public void VXTTZXXID(String str) {
        Toast.makeText(XiaoxiActivity.this,str,Toast.LENGTH_SHORT).show();
        myTongzhiAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pxttz.onDestory();
        pxttz=null;
    }
}
