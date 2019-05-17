package com.bw.movie.wdyy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.YYXQ_YYAdapter;
import com.bw.movie.wdyy.adapter.YYXQ_YYPLAdapter;
import com.bw.movie.wdyy.bean.YypjBean;
import com.bw.movie.wdyy.bean.YyxqBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.fragment.Fragment2;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.RecyclerCoverFlow;

public class YYXiangqingActivity extends AppCompatActivity implements ContractInterface.VXQPL {

    @BindView(R.id.yyxq_sim)
    SimpleDraweeView yyxq_sim;
    @BindView(R.id.yyxq_title)
    TextView yyxq_title;
    @BindView(R.id.yyxq_address)
    TextView yyxq_address;
    @BindView(R.id.image_xiang)
    ImageView imageXiang;
    @BindView(R.id.recycler_coverflow)
    RecyclerCoverFlow recyclerCoverflow;
    @BindView(R.id.yyxq_linearlayout)
    LinearLayout yyxqLinearlayout;
    String cinemaId;
    PopupWindow window;
    Context context;
    //详情
    YYXQ_YYAdapter yyxqAdapter;
    List<YyxqBean.ResultBean> xq_list=new ArrayList<>();
    ImageView yyxq_fanhui_img;
    //评论
    YYXQ_YYPLAdapter yyplAdapter;
    List<YypjBean.ResultBean> pj_list=new ArrayList<>();
    //按钮
    Button xiangqing;
    Button pinglun;
    RecyclerView yyxq_recyclerView;
    //P
    ContractInterface.PXQPL pxqpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yyxiangqing);
        ButterKnife.bind(this);
        //将数据添加
        TianjiashujvInit();
        pxqpl=new MyPresenter(this);
        //设置点击弹框
        LinearlayoutInit(context);

    }
    //点击添加弹框
    private void LinearlayoutInit(final Context context) {
        this.context=context;
        yyxqLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(YYXiangqingActivity.this,R.layout.yyxq_linear,null);
                window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setFocusable(true);
                ColorDrawable dw = new ColorDrawable(0xffffffff);
                window.setBackgroundDrawable(dw);
                window.setAnimationStyle(R.style.PopupAnimation);
                window.showAsDropDown(yyxqLinearlayout);
                yyxq_recyclerView=view.findViewById(R.id.yyxq_recyclerview);
                xiangqing=view.findViewById(R.id.yyxq_xiangqing);
                pinglun=view.findViewById(R.id.yyxq_pinglun);
                yyxq_fanhui_img=view.findViewById(R.id.yyxq_shouqi_img);
                //设置管理器
                LinearLayoutManager layoutManager=new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                yyxq_recyclerView.setLayoutManager(layoutManager);
                //设置详情的点击事件
                xiangqingInit();
                //设置评论的点击事件
                PinglunInit();
                //设置收起的点击事件
                shouqiInit();

            }
        });
    }
    //评论
    private void PinglunInit() {
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xq_list.clear();
                pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));
                xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));
                //设置适配器
                yyplAdapter=new YYXQ_YYPLAdapter(pj_list,YYXiangqingActivity.this);
                yyxq_recyclerView.setAdapter(yyplAdapter);
                //去P
                pxqpl.Pyypj(cinemaId,1,5);
            }
        });
    }

    //收起弹框
    private void shouqiInit() {
        yyxq_fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }

    //详情
    private void xiangqingInit() {
        xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xq_list.clear();
                xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));
                pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));
                //设置适配器
                yyxqAdapter=new YYXQ_YYAdapter(xq_list,YYXiangqingActivity.this);
                yyxq_recyclerView.setAdapter(yyxqAdapter);
                //去P层
                pxqpl.Pyyxq(cinemaId);
            }
        });
    }

    private void TianjiashujvInit() {
        Intent intent = getIntent();
        String yYid = intent.getStringExtra("YYid");
        String titles = intent.getStringExtra("titles");
        String address = intent.getStringExtra("address");
        String log = intent.getStringExtra("log");
        yyxq_title.setText(titles);
        yyxq_address.setText(address);
        yyxq_sim.setImageURI(log);
        cinemaId=yYid;
    }

    @Override
    public void Vyyxq(YyxqBean yyxqBea) {
        this.xq_list.add(yyxqBea.getResult());
        Log.e("abc", "Vyyxq: "+yyxqBea.getResult().getVehicleRoute() );
        yyxqAdapter.notifyDataSetChanged();
    }

    @Override
    public void Vyypj(List<YypjBean.ResultBean> lst) {
        this.pj_list.addAll(lst);
        yyxqAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pxqpl.onDestory();
        pxqpl=null;
    }
}
