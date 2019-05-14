package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class GuanZhuyingyuanAdapter extends RecyclerView.Adapter<GuanZhuyingyuanAdapter.holder>{
    List<GZYYBean.ResultBean> list;
    Context context;

    public GuanZhuyingyuanAdapter(List<GZYYBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.gzyy_adapter, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.guanzhu_sim.setImageURI(list.get(i).getLogo());
        holder.gzyy_title.setText(list.get(i).getName());
        holder.gzyy_addrees.setText(list.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView guanzhu_sim;
        TextView gzyy_title,gzyy_addrees;
        public holder(@NonNull View itemView) {
            super(itemView);
            guanzhu_sim=itemView.findViewById(R.id.gzyy_sim);
            gzyy_title=itemView.findViewById(R.id.gzyy_name);
            gzyy_addrees=itemView.findViewById(R.id.gzyy_addrees);
        }
    }
}
