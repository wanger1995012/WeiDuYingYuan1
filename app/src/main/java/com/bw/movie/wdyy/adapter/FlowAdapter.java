package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/13 10:47
 * @Description：描述信息
 */
public class FlowAdapter extends RecyclerCoverFlow.Adapter<FlowAdapter.Holder> {
    List<NowPlayingBean.ResultBean> list ;
    Context context;
    private static String strMinute;

    public FlowAdapter(List<NowPlayingBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.flow_layout,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.img.setImageURI(list.get(i).getImageUrl());
        holder.name.setText(list.get(i).getName());
        long times = list.get(i).getReleaseTime();

        holder.time.setText((times/1000/24/60/10000)+"分钟");
        formatTime(times);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerCoverFlow.ViewHolder{

        SimpleDraweeView img;
        TextView name,time;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_flow);
            name = itemView.findViewById(R.id.text_name_flow);
            time = itemView.findViewById(R.id.text_time_flow);
        }
    }


    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        //分钟
        strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strMinute + " 分钟 " + strSecond + " 秒";

    }



}
