package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/13 10:47
 * @Description：描述信息
 */
public class JiAdapter extends RecyclerView.Adapter<JiAdapter.Holder> {
    List<ComingSoonBean.ResultBean> list ;
    Context context;

    public JiAdapter(List<ComingSoonBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.ji_layout,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.img.setImageURI(list.get(i).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView img;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_ji);
        }
    }
}
