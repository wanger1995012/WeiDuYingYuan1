package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.GouPiaoBean;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是购票的适配器
 */
public class MyGoupiaoAdapter extends RecyclerView.Adapter<MyGoupiaoAdapter.holder>{
    List<GouPiaoBean.ResultBean> list;
    Context context;

    public MyGoupiaoAdapter(List<GouPiaoBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView goupiao_Time,Goupiao_amout,goupiao_kaishi,goupiao_jieshu,goupiao_hall,
        goupiao_pice,goupia_dianying,goupiao_yingyuan,goupiao_dingdan,goupiao_zhuangtai;
        public holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
