package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bw.movie.R;
import com.bw.movie.bean.YYLunboBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class YYLunboAdapter extends RecyclerCoverFlow.Adapter<YYLunboAdapter.Holder> {
    List<YYLunboBean.ResultBean> list;
    Context context;
    TTMycall ttMycall;
    public YYLunboAdapter(List<YYLunboBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public YYLunboAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.flow_layout, null);
        return new YYLunboAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final YYLunboAdapter.Holder holder, final int i) {
        holder.img.setImageURI(list.get(i).getImageUrl());
        holder.name.setText(list.get(i).getName());
        holder.time.setText(list.get(i).getDuration());
        //设置点击事件
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttMycall.yypiaojia(list,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerCoverFlow.ViewHolder {

        SimpleDraweeView img;
        TextView name, time;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_flow);
            name = itemView.findViewById(R.id.text_name_flow);
            time = itemView.findViewById(R.id.text_time_flow);
        }
    }
    public interface TTMycall{
        public void yypiaojia(List<YYLunboBean.ResultBean> lst,int i);
    }

    public void setTtMycall(TTMycall ttMycall) {
        this.ttMycall = ttMycall;
    }
}
