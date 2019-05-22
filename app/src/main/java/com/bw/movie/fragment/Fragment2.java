package com.bw.movie.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bw.movie.R;
import com.bw.movie.adapter.TuijianAdapter;
import com.bw.movie.bean.TuijianBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.dongtai.PermissionsUtils;
import com.bw.movie.presenter.MyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:32
 * @Description：描述信息
 */
public class Fragment2 extends Fragment implements ContractInterface.VYingyuan {


    int isShow = 1;
    @BindView(R.id.image_fiml_loca)
    ImageView imageFimlLoca;
    @BindView(R.id.text_fiml_loca)
    TextView textFimlLoca;
    @BindView(R.id.image_sou)
    ImageView imageSou;
    @BindView(R.id.yingyuan_edit)
    EditText yingyuan_edit;
    @BindView(R.id.text_fiml_se)
    TextView textFimlSe;
    @BindView(R.id.layout_fiml_se)
    RelativeLayout layoutFimlSe;
    Unbinder unbinder;

    @BindView(R.id.yingyuan_fujin)
    Button yingyuanFujin;
    @BindView(R.id.yingyuan_viewpager)
    XRecyclerView yingyuanrecycler;

    //设置list集合
    int page = 1;
    List<TuijianBean.ResultBean> list = new ArrayList<>();
    TuijianAdapter tuijianAdapter;
    ContractInterface.PYingyuan pYingyuan;
    @BindView(R.id.yingyuan_tuijian)
    Button yingyuanTuijian;
    @BindView(R.id.yingyuan_radio)
    LinearLayout yingyuanRadio;
    Unbinder unbinder1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout2, null);
        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置定位
        DingweiInit();
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow == 1) {
                    textFimlSe.setVisibility(View.VISIBLE);
                    yingyuan_edit.setVisibility(View.VISIBLE);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", 0, -290);
                    animator.setDuration(1500);
                    animator.start();
                    isShow = 2;
                }
            }
        });

        //点击搜索缩进去
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow == 2) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(layoutFimlSe, "translationX", -290, 0);
                    animator.setDuration(1500);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    yingyuan_edit.setVisibility(View.GONE);
                    isShow = 1;
                }
            }
        });
        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yingyuanrecycler.setLayoutManager(layoutManager);
        //设置P层
        pYingyuan = new MyPresenter(this);
        pYingyuan.PTuijian(1, 10);
        //进入后首先展示
        zhanshi1();
        //设置推荐影院的点击搜索
        TuijianCacli();
        //设置附近影院的点击搜索
        FujinCacli();
        //设置影院的搜索框
        MoHucaxun();


        /**
         * 定位权限
         * Manifest.permission.ACCESS_FINE_LOCATION
         * Manifest.permission.ACCESS_COARSE_LOCATION
         * 相机相册权限
         * Manifest.permission.CAMERA
         * 手机状态权限
         * Manifest.permission.READ_CALL_LOG,
         * Manifest.permission.READ_PHONE_STATE,
         * Manifest.permission.CALL_PHONE,
         * Manifest.permission.WRITE_CALL_LOG,
         * Manifest.permission.USE_SIP,
         * Manifest.permission.PROCESS_OUTGOING_CALLS,
         * Manifest.permission.ADD_VOICEMAIL
         * 读写权限
         * Manifest.permission.READ_EXTERNAL_STORAGE,
         * Manifest.permission.WRITE_EXTERNAL_STORAGE
         *
         */
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.ADD_VOICEMAIL,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
//        PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //创建监听权限的接口对象
        PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {
                Toast.makeText(getContext(), "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void forbitPermissons() {
//            finish();
                Toast.makeText(getContext(), "权限不通过!", Toast.LENGTH_SHORT).show();
            }
        };
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(getActivity(), permissions, permissionsResult);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }

    //定位
    private void DingweiInit() {
        initLocationOption();

    }

    private void zhanshi1() {
        //设置按钮的点击监听
        yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.shap1));
        yingyuanTuijian.setTextColor(Color.WHITE);
        yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.myshap1));
        yingyuanFujin.setTextColor(Color.BLACK);
        list.clear();
        //设置适配器
        tuijianAdapter = new TuijianAdapter(list, getActivity());
        yingyuanrecycler.setAdapter(tuijianAdapter);
        pYingyuan.PTuijian(1, 10);
        //设置上下拉的监听
        yingyuanrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                pYingyuan.PTuijian(1, 10);
            }

            @Override
            public void onLoadMore() {
                //上拉加载
                page++;
                pYingyuan.PTuijian(page, 10);
            }
        });
        tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
            @Override
            public void weiGuanzhu(String str) {
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void QvxiaoGuanzhu(String str) {
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void MoHucaxun() {
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanTuijian.setTextColor(Color.BLACK);
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanFujin.setTextColor(Color.BLACK);
                //获取搜索框的内容
                String cinemaName = yingyuan_edit.getText().toString();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PYYMhucaxun(cinemaName, 1, 10);

                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }

    //设置附近影院的点击搜索
    private void FujinCacli() {

        yingyuanFujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置按钮的点击监听
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.shap1));
                yingyuanFujin.setTextColor(Color.WHITE);
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanTuijian.setTextColor(Color.BLACK);
                list.clear();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PFujin("103.56553241213687","28.997989569889246",1, 10);
                //设置上下拉的监听
                yingyuanrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷新
                        pYingyuan.PFujin("103.56553241213687","28.997989569889246", 1, 10);
                    }

                    @Override
                    public void onLoadMore() {
                        //上拉加载
                        page++;
                        pYingyuan.PFujin("103.56553241213687","28.997989569889246", page, 10);
                    }
                });
                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //设置推荐影院的点击搜索
    private void TuijianCacli() {
        yingyuanTuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置按钮的点击监听
                yingyuanTuijian.setBackground(getResources().getDrawable(R.drawable.shap1));
                yingyuanTuijian.setTextColor(Color.WHITE);
                yingyuanFujin.setBackground(getResources().getDrawable(R.drawable.myshap1));
                yingyuanFujin.setTextColor(Color.BLACK);
                list.clear();
                //设置适配器
                tuijianAdapter = new TuijianAdapter(list, getActivity());
                yingyuanrecycler.setAdapter(tuijianAdapter);
                pYingyuan.PTuijian(1, 10);
                //设置上下拉的监听
                yingyuanrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷新
                        pYingyuan.PTuijian(1, 10);
                    }

                    @Override
                    public void onLoadMore() {
                        //上拉加载
                        page++;
                        pYingyuan.PTuijian(page, 10);
                    }
                });
                tuijianAdapter.setMyCall(new TuijianAdapter.MyCall() {
                    @Override
                    public void weiGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void QvxiaoGuanzhu(String str) {
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public void VTuijian(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void VFujin(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void VYYMohuca(List<TuijianBean.ResultBean> lst) {
        yingyuanrecycler.loadMoreComplete();
        yingyuanrecycler.refreshComplete();
        list.clear();
        this.list.addAll(lst);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pYingyuan.onDestory();
        pYingyuan = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
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
            textFimlLoca.setText(location.getAddress().address);
        }
    }
}
