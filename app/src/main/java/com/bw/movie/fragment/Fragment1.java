package com.bw.movie.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bw.movie.R;
import com.bw.movie.adapter.FlowAdapter;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.JiAdapter;
import com.bw.movie.adapter.ReAdapter;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.Commingbean;
import com.bw.movie.bean.HotMovieListBean;
import com.bw.movie.bean.Hotbean;
import com.bw.movie.bean.NowPlayingBean;
import com.bw.movie.bean.Nowbean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.dao.greendao.gen.DaoMaster;
import com.bw.movie.dao.greendao.gen.HotbeanDao;
import com.bw.movie.hotactivity.DetailsActivity;
import com.bw.movie.hotactivity.HotActivity;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.utile.RetrofitUtil;
import com.bw.movie.view.App;

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
    public  boolean netWork;


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
        //定位
        DingweiInit();
        netWork = RetrofitUtil.isNetWork(getContext());
        if(netWork){
            p.toModel1();
            p.toModel2();
            p.toModel3();
        }else{
            //第1个RecyclerView 没网显示
            List<Hotbean> hotbeans1 = App.daoSession2.loadAll(Hotbean.class);
            for (int i = 0; i < hotbeans1.size(); i++) {
                HotMovieListBean.ResultBean bean = new HotMovieListBean.ResultBean();
                bean.setFollowMovie(hotbeans1.get(i).getFollowMovie());
                bean.setId(hotbeans1.get(i).getId());
                bean.setImageUrl(hotbeans1.get(i).getImageUrl());
                bean.setName(hotbeans1.get(i).getName());
                bean.setRank(hotbeans1.get(i).getRank());
                bean.setSummary(hotbeans1.get(i).getSummary());
                bean1.add(bean);
            }
            //第二个RecyclerView 没网显示
            List<Nowbean> nowbeans = App.daoSession2.loadAll(Nowbean.class);
            for (int i = 0; i < nowbeans.size(); i++) {
                NowPlayingBean.ResultBean bean = new NowPlayingBean.ResultBean();
                bean.setFollowMovie(hotbeans1.get(i).getFollowMovie());
                bean.setId(hotbeans1.get(i).getId());
                bean.setImageUrl(hotbeans1.get(i).getImageUrl());
                bean.setName(hotbeans1.get(i).getName());
                bean.setRank(hotbeans1.get(i).getRank());
                bean.setSummary(hotbeans1.get(i).getSummary());
                bean2.add(bean);
            }
            //第3个RecyclerView 没网显示
            List<Commingbean> commingbeans = App.daoSession2.loadAll(Commingbean.class);
            for (int i = 0; i < commingbeans.size(); i++) {
                ComingSoonBean.ResultBean bean = new ComingSoonBean.ResultBean();
                bean.setFollowMovie(hotbeans1.get(i).getFollowMovie());
                bean.setId(hotbeans1.get(i).getId());
                bean.setImageUrl(hotbeans1.get(i).getImageUrl());
                bean.setName(hotbeans1.get(i).getName());
                bean.setRank(hotbeans1.get(i).getRank());
                bean.setSummary(hotbeans1.get(i).getSummary());
                bean3.add(bean);
            }
            //适配器
            setRc1();
            setRc2();
            setRc3();
            setFlow();
        }
        //适配器
        setRc1();
        setRc2();
        setRc3();
        hotClick();
        TranSlate();//平移
        //轮播的选中监听
        flow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                Point p = new Point();
                //获取窗口管理器
                WindowManager wm = (WindowManager) getActivity().getSystemService(getContext().WINDOW_SERVICE);
                wm.getDefaultDisplay().getSize(p);
                int white = p.x;
                //宽除以旋转木马的图片的bean的长度
                //得到每一个所占的宽度
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

    private void DingweiInit() {
        initLocationOption();
    }

    private void TranSlate() {
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShow == 1){
                    textFimlSe.setVisibility(View.VISIBLE);
                    edFimlSe.setVisibility(View.VISIBLE);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",0,-290);
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
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",-290,0);
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
                if(netWork){
                    Intent intent = new Intent(getContext(),DetailsActivity.class);
                    intent.putExtra("MovieId",position+"");
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "请检查网络连接后再点击",Toast.LENGTH_LONG).show();
                }

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
                if(netWork){
                    Intent intent = new Intent(getContext(),DetailsActivity.class);
                    intent.putExtra("MovieId",position+"");
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "请检查网络连接后再点击",Toast.LENGTH_LONG).show();
                }
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
                if(netWork){
                    Intent intent = new Intent(getContext(),DetailsActivity.class);
                    intent.putExtra("MovieId",position+"");
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "请检查网络连接后再点击",Toast.LENGTH_LONG).show();
                }
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
                if(netWork){
                    Intent intent = new Intent(getContext(),DetailsActivity.class);
                    intent.putExtra("MovieId",position+"");
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "请检查网络连接后再点击",Toast.LENGTH_LONG).show();
                }
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

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        Fragment1.MyLocationListener myLocationListener = new Fragment1.MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }

    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            text_dingwei.setText(location.getAddress().address);
        }
    }
}
