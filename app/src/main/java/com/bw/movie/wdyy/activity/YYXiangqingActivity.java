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
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.YYLunboAdapter;
import com.bw.movie.wdyy.adapter.YYPiaojiaAdapter;
import com.bw.movie.wdyy.adapter.YYXQ_YYAdapter;
import com.bw.movie.wdyy.adapter.YYXQ_YYPLAdapter;
import com.bw.movie.wdyy.bean.YYLunboBean;
import com.bw.movie.wdyy.bean.YYPiaojiaBean;
import com.bw.movie.wdyy.bean.YypjBean;
import com.bw.movie.wdyy.bean.YyxqBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.RecyclerCoverFlow;

public class YYXiangqingActivity extends AppCompatActivity implements ContractInterface.VXQPL, ContractInterface.VYYDZ {

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
    ImageView yypj_xiepinglun;
    //详情
    YYXQ_YYAdapter yyxqAdapter;
    List<YyxqBean.ResultBean> xq_list = new ArrayList<>();
    ImageView yyxq_fanhui_img;
    //评论
    YYXQ_YYPLAdapter yyplAdapter;
    List<YypjBean.ResultBean> pj_list = new ArrayList<>();
    ContractInterface.PYYDZ pyydz;
    //按钮
    Button xiangqing;
    Button pinglun;
    RecyclerView yyxq_recyclerView;
    //P
    ContractInterface.PXQPL pxqpl;
    //旋转木马
    List<YYLunboBean.ResultBean> bean2 = new ArrayList<>();
    YYLunboAdapter adapter4;
    @BindView(R.id.yy_piaojia)
    RecyclerView yyPiaojia;
    //影院票价
    YYPiaojiaAdapter yyPiaojiaAdapter;
    @BindView(R.id.yy_fanhui)
    ImageView yyFanhui;
    List<YYPiaojiaBean.ResultBean> piaojia_list=new ArrayList<>();
    int dianyingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yyxiangqing);
        ButterKnife.bind(this);
        //将数据添加
        TianjiashujvInit();
        pxqpl = new MyPresenter(this);
        //设置点击弹框
        LinearlayoutInit(context);
        pyydz = new MyPresenter(this);
        //recyclerCoverflow的轮播
        LunBoInit();
        //设置返回按钮
        fanhuiInit();
        //设置影院票价
        PiaojiaoInit();
    }
    //返回
    private void fanhuiInit() {
        yyFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //影院票价
    private void PiaojiaoInit() {
        //yyPiaojia
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yyPiaojia.setLayoutManager(layoutManager);
        //设置适配器
        yyPiaojiaAdapter=new YYPiaojiaAdapter(piaojia_list,YYXiangqingActivity.this);
        yyPiaojia.setAdapter(yyPiaojiaAdapter);
        //去P层
        pxqpl.Pyypiaojia(cinemaId,dianyingID);
    }

    //旋转木马
    private void LunBoInit() {
        //创建适配器
        adapter4 = new YYLunboAdapter(bean2, YYXiangqingActivity.this);
        recyclerCoverflow.setAdapter(adapter4);
        //去P
        pxqpl.PyyLB(cinemaId);
        //设置适配器
        adapter4.setTtMycall(new YYLunboAdapter.TTMycall() {
            @Override
            public void yypiaojia(List<YYLunboBean.ResultBean> lt, int i) {
                dianyingID=lt.get(i).getId();
                //设置影院票价
                PiaojiaoInit();
                yyPiaojiaAdapter.notifyDataSetChanged();
            }
        });
    }

    //点击添加弹框
    private void LinearlayoutInit(final Context context) {
        this.context = context;
        yyxqLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(YYXiangqingActivity.this, R.layout.yyxq_linear, null);
                window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setFocusable(true);
                ColorDrawable dw = new ColorDrawable(0xffffffff);
                window.setBackgroundDrawable(dw);
                window.setAnimationStyle(R.style.PopupAnimation);
                window.showAsDropDown(yyxqLinearlayout);
                yyxq_recyclerView = view.findViewById(R.id.yyxq_recyclerview);
                xiangqing = view.findViewById(R.id.yyxq_xiangqing);
                pinglun = view.findViewById(R.id.yyxq_pinglun);
                yyxq_fanhui_img = view.findViewById(R.id.yyxq_shouqi_img);
                yypj_xiepinglun = view.findViewById(R.id.yypj_xiepinglun);
                //设置管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                yyxq_recyclerView.setLayoutManager(layoutManager);
                //设置进入后加载
                xq_list.clear();
                xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));
                pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));
                //设置适配器
                yyxqAdapter = new YYXQ_YYAdapter(xq_list, YYXiangqingActivity.this);
                yyxq_recyclerView.setAdapter(yyxqAdapter);
                //去P层
                pxqpl.Pyyxq(cinemaId);
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
                yyplAdapter = new YYXQ_YYPLAdapter(pj_list, YYXiangqingActivity.this);
                yyxq_recyclerView.setAdapter(yyplAdapter);
                //去P
                pxqpl.Pyypj(cinemaId, 1, 5);

                //设置适配
                yyplAdapter.setMyCall(new YYXQ_YYPLAdapter.MyCall() {
                    @Override
                    public void YYDZ(List<YypjBean.ResultBean> lst, int i) {
                        pyydz.PYYDianzan(lst.get(i).getCommentId());
                    }
                });
                //设置写评论的点击事件
                yypj_xiepinglun.setVisibility(View.VISIBLE);
                yypj_xiepinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YYXiangqingActivity.this, XiePinglunActivity.class);
                        intent.putExtra("cinemaId", cinemaId);
                        startActivity(intent);
                    }
                });
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
                yyxqAdapter = new YYXQ_YYAdapter(xq_list, YYXiangqingActivity.this);
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
        cinemaId = yYid;
    }

    @Override
    public void Vyyxq(YyxqBean yyxqBea) {
        this.xq_list.add(yyxqBea.getResult());
        yyxqAdapter.notifyDataSetChanged();
    }

    @Override
    public void VyyLB(List<YYLunboBean.ResultBean> lst) {
        this.bean2.addAll(lst);
        Log.e("a123", "VyyLB: " + lst.get(0).getId());
        adapter4.notifyDataSetChanged();
    }

    @Override
    public void VyyPiaojia(List<YYPiaojiaBean.ResultBean> lst) {
        this.piaojia_list.addAll(lst);
        yyPiaojiaAdapter.notifyDataSetChanged();
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
        pxqpl = null;
    }

    @Override
    public void VYYDianzan(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
