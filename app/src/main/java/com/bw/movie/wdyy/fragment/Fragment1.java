package com.bw.movie.wdyy.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.adapter.FlowAdapter;
import com.bw.movie.wdyy.adapter.HotAdapter;
import com.bw.movie.wdyy.adapter.JiAdapter;
import com.bw.movie.wdyy.adapter.ReAdapter;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.hotactivity.DetailsActivity;
import com.bw.movie.wdyy.hotactivity.HotActivity;
import com.bw.movie.wdyy.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:32
 * @Description：描述信息
 */
public class Fragment1 extends Fragment implements ContractInterface.ViewMovieList {
    List<HotMovieListBean.ResultBean> bean1 = new ArrayList<>();
    List<NowPlayingBean.ResultBean>   bean2 = new ArrayList<>();
    List<ComingSoonBean.ResultBean>   bean3 = new ArrayList<>();
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    int a;
    RecyclerCoverFlow flow;
    RecyclerView rc1,rc2,rc3;
    RelativeLayout hot,re,ji;
    private HotAdapter adapter1;
    private ReAdapter adapter2;
    private JiAdapter adapter3;
    private FlowAdapter adapter4;
    ImageView image_dingwei;
    TextView text_dingwei;
    ImageView imageFimlLoca;
    TextView textFimlLoca;
    ImageView image,image_hua;
    EditText edFimlSe;
    TextView textFimlSe;
    RelativeLayout layoutFimlSe;
    int isShow = 1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout1,null);
        flow= view.findViewById(R.id.recycler_cover_flow);
        rc1 = view.findViewById(R.id.recycler_view_hot_movie);
        rc2 = view.findViewById(R.id.recycler_view_re_movie);
        rc3 = view.findViewById(R.id.recycler_view_ji_movie);
        hot = view.findViewById(R.id.lie1);
        re = view.findViewById(R.id.lie2);
        ji = view.findViewById(R.id.lie3);
        image_dingwei = view.findViewById(R.id.image_dingwei);
        text_dingwei = view.findViewById(R.id.text_dingwei);
        image_hua = view.findViewById(R.id.image_hua);
        imageFimlLoca = view.findViewById(R.id.image_fiml_loca);
        textFimlLoca = view.findViewById(R.id.text_fiml_loca);
        image = view.findViewById(R.id.image_sou);
        edFimlSe = view.findViewById(R.id.ed_fiml_se);
        textFimlSe = view.findViewById(R.id.text_fiml_se);
        layoutFimlSe = view.findViewById(R.id.layout_fiml_se);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRc1();
        setRc2();
        setRc3();
        p.toModel1();
        p.toModel2();
        p.toModel3();
        hotClick();
        TranSlate();//平移
        flow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                Point p = new Point();
                //获取窗口管理器
                WindowManager wm = (WindowManager) getActivity().getSystemService(getContext().WINDOW_SERVICE);
                wm.getDefaultDisplay().getSize(p);
                int white = p.x;
                int i = white / bean2.size();

                if(position == 0){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",0,0);
                    animator.start();
                }else if(position == 1){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",0,i);
                    animator.start();
                }else if(position == 2){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",i,2*i);
                    animator.start();
                }else if(position == 3){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",2*i,3*i);
                    animator.start();
                }else if(position == 4){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",3*i,4*i);
                    animator.start();
                }else if(position == 5){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",4*i,5*i);
                    animator.start();
                }else if(position == 6){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",5*i,6*i);
                    animator.start();
                }else if(position == 7){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",6*i,7*i);
                    animator.start();
                }else if(position == 8){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",7*i,8*i);
                    animator.start();
                }else if(position == 9){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",8*i,9*i);
                    animator.start();
                }else if(position == 10){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",9*i,10*i);
                    animator.start();
                }else if(position == 11){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",10*i,11*i);
                    animator.start();
                }else if(position == 12){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",11*i,12*i);
                    animator.start();
                }else if(position == 13){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",12*i,13*i);
                    animator.start();
                }else if(position == 14){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",14*i,15*i);
                    animator.start();
                }else if(position == 15){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",16*i,17*i);
                    animator.start();
                }else if(position == 16){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",18*i,19*i);
                    animator.start();
                }else if(position == 17){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",19*i,20*i);
                    animator.start();
                }else if(position == 18){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",21*i,22*i);
                    animator.start();
                }else if(position == 19){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(image_hua,"translationX",22*i,23*i);
                    animator.start();
                }else{
                    return;
                }
            }
        });


    }

    private void TranSlate() {
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShow == 1){
                    textFimlSe.setVisibility(View.VISIBLE);
                    edFimlSe.setVisibility(View.VISIBLE);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",0,-200);
                    animator.setDuration(1500);
                    animator.start();
                    isShow=2;
                }
            }
        });

        //点击搜索缩进去
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow==2){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",-200,0);
                    animator.setDuration(1500);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    edFimlSe.setVisibility(View.GONE);
                    isShow=1;
                }
            }
        });

    }

    private void hotClick() {
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HotActivity.class));
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HotActivity.class));
            }
        });
        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HotActivity.class));
            }
        });
    }

    private void setFlow() {
        //创建适配器
        adapter4 = new FlowAdapter(bean2, getContext());
        flow.setAdapter(adapter4);
        adapter4.setListener(new FlowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("MovieId",position+"");
                startActivity(intent);
            }
        });
    }

    private void setRc3() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc3.setLayoutManager(manager);
        //创建适配器
        adapter3 = new JiAdapter(bean3, getContext());
        rc3.setAdapter(adapter3);
        adapter3.setListener(new JiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("MovieId",position+"");
                startActivity(intent);
            }
        });
    }

    private void setRc2() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc2.setLayoutManager(manager);
        //创建适配器
        adapter2 = new ReAdapter(bean2, getContext());
        rc2.setAdapter(adapter2);
        adapter2.setListener(new ReAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("MovieId",position+"");
                startActivity(intent);
            }
        });
    }


    private void setRc1() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc1.setLayoutManager(manager);
        //创建适配器
        adapter1 = new HotAdapter(bean1, getContext());
        rc1.setAdapter(adapter1);
        adapter1.setListener(new HotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("MovieId",position+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void ShowMovieList1(HotMovieListBean bean) {
        List<HotMovieListBean.ResultBean> list = bean.getResult();
        bean1.addAll(list);
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void ShowMovieList2(NowPlayingBean bean) {
        List<NowPlayingBean.ResultBean> list = bean.getResult();
        bean2.addAll(list);
        adapter2.notifyDataSetChanged();
        setFlow();
    }

    @Override
    public void ShowMovieList3(ComingSoonBean bean) {
        List<ComingSoonBean.ResultBean> list = bean.getResult();
        bean3.addAll(list);
        adapter3.notifyDataSetChanged();
    }
}
