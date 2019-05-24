package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ScheduleBean;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/21 14:18
 * @Description：描述信息
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Holder> {
    List<ScheduleBean.ResultBean> list;
    Context context;
    MyCallBack myCallBack;

    public ScheduleAdapter(List<ScheduleBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_layout, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        final String screeningHall = list.get(i).getScreeningHall();
        holder.piaoTing.setText(screeningHall);
        final String beginTime = list.get(i).getBeginTime();
        holder.piaoBeginTime.setText(beginTime);
        final String endTime = list.get(i).getEndTime();
        holder.piaoEndTime.setText(endTime);
        final double price = list.get(i).getPrice();
        holder.piaoPrice.setText(price+"");

        holder.imageIntoXuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.clickListener(holder.itemView,beginTime,endTime,screeningHall,price,list.get(i).getId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView  piaoTing;
        TextView  piaoBeginTime;
        TextView  piaoEndTime;
        ImageView imageIntoXuan;
        TextView  piaoPrice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            piaoTing = itemView.findViewById(R.id.piao_ting);
            piaoBeginTime = itemView.findViewById(R.id.piao_begin_time);
            piaoEndTime = itemView.findViewById(R.id.piao_end_time);
            imageIntoXuan = itemView.findViewById(R.id.image_into_xuan);
            piaoPrice = itemView.findViewById(R.id.piao_price);
        }
    }
    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface MyCallBack{
        public void clickListener(View view, String begin, String end, String ting,double price,String id);
    }
}
