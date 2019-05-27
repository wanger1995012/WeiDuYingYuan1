package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bw.movie.R;
import com.bw.movie.bean.YYPiaojiaBean;
import com.bw.movie.hotactivity.XuanZuoActivity;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class YYPiaojiaAdapter extends RecyclerView.Adapter<YYPiaojiaAdapter.holder>{
    List<YYPiaojiaBean.ResultBean> list;
    Context context;
    public YYPiaojiaAdapter(List<YYPiaojiaBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.piaojia_adapter, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, final int i) {
        holder.piaojia_pice.setText(list.get(i).getPrice()+"");
        holder.piaojia_begin.setText(list.get(i).getBeginTime());
        holder.piaojia_end.setText(list.get(i).getEndTime());
        holder.piaojia_shichang.setText(list.get(i).getDuration());
        holder.piaojia_hall.setText(list.get(i).getScreeningHall());
        //设置按钮的点击前往购票页
        holder.yy_piaojian_goupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,XuanZuoActivity.class);
                intent.putExtra("begin",list.get(i).getBeginTime());
                intent.putExtra("end",list.get(i).getEndTime());
                intent.putExtra("ting",list.get(i).getScreeningHall());
                intent.putExtra("price" , list.get(i).getPrice()+"");
                intent.putExtra("id" , list.get(i).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        ImageView yy_piaojian_goupiao;
        TextView piaojia_hall,piaojia_begin,piaojia_end,piaojia_pice,piaojia_shichang;
        public holder(@NonNull View itemView) {
            super(itemView);
            yy_piaojian_goupiao=itemView.findViewById(R.id.piaojia_zhifu);
            piaojia_hall=itemView.findViewById(R.id.piaojia_dating);
            piaojia_begin=itemView.findViewById(R.id.piaojia_kaishi);
            piaojia_end=itemView.findViewById(R.id.piaojia_jieshu);
            piaojia_shichang=itemView.findViewById(R.id.piaojia_shichang);
            piaojia_pice=itemView.findViewById(R.id.piaojia_jiage);
        }
    }
}
