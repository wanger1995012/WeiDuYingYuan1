package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.GZDYBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class GuanzhuDianyingAdapter extends RecyclerView.Adapter<GuanzhuDianyingAdapter.holder>{
    List<GZDYBean.ResultBean> list;
    Context context;
    long dateTime;
    public GuanzhuDianyingAdapter(List<GZDYBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.gzdy_adapter, null);
        return new holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.GZDY_sim.setImageURI(list.get(i).getImageUrl());
        holder.GZDY_title.setText(list.get(i).getName());
        holder.GZDY_summary.setText(list.get(i).getSummary());
        //将毫秒值转换为年月日
        dateTime=list.get(i).getReleaseTime();
        //将毫秒值转换为年月日
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String time =  formatter.format(dateTime);
        holder.GZDY_releaseTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView GZDY_sim;
        TextView GZDY_title,GZDY_summary,GZDY_releaseTime;
        public holder(@NonNull View itemView) {
            super(itemView);
            GZDY_sim=itemView.findViewById(R.id.gzdy_sim);
            GZDY_title=itemView.findViewById(R.id.gzdy_name);
            GZDY_summary=itemView.findViewById(R.id.gzdy_summes);
            GZDY_releaseTime=itemView.findViewById(R.id.gzdy_timout);
        }
    }
}
