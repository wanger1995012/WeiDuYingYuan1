package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bw.movie.R;
import com.bw.movie.bean.YyxqBean;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class YYXQ_YYAdapter extends RecyclerView.Adapter<YYXQ_YYAdapter.holder>{
    List<YyxqBean.ResultBean> list;
    Context context;

    public YYXQ_YYAdapter(List<YyxqBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.yyxq_liner_adapter, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.text_address.setText(list.get(i).getAddress());
        holder.text_phone.setText(list.get(i).getPhone());
        holder.text_vehicleRoute.setText(list.get(i).getVehicleRoute());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView text_address,text_phone,text_vehicleRoute;
        public holder(@NonNull View itemView) {
            super(itemView);
            text_address=itemView.findViewById(R.id.yyxq_liner_address);
            text_phone=itemView.findViewById(R.id.yyxq_liner_dianhua);
            text_vehicleRoute=itemView.findViewById(R.id.yyxq_liner_luxian);
        }
    }
}
