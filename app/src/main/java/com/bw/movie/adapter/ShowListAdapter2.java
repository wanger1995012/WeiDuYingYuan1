package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/14 10:09
 * @Description：描述信息
 */
public class ShowListAdapter2 extends RecyclerView.Adapter<ShowListAdapter2.Holder> {
    List<ComingSoonBean.ResultBean> list;
    Context context;
    OnItemClickListener listener;
    public ShowListAdapter2(List<ComingSoonBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_zhan2,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.big_img.setImageURI(list.get(i).getImageUrl());
        holder.title.setText(list.get(i).getName());
        holder.jianjie.setText(list.get(i).getSummary());
        final int status = list.get(i).getFollowMovie();
        Glide.with(context).load(R.drawable.fenge).into(holder.fenge);
        int movie = list.get(i).getFollowMovie();
        if(movie == 1){
            Glide.with(context).load(R.drawable.zanb).into(holder.zan);
        }else if(movie == 2){
            Glide.with(context).load(R.drawable.zanh).into(holder.zan);
        }
        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                int movie = list.get(i).getFollowMovie();
                if(movie == 1){
                    list.get(i).setFollowMovie(2);
                }else if(movie == 2){
                    list.get(i).setFollowMovie(1);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(i).getId();
                listener.onItemClick(holder.itemView, id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView big_img;
        ImageView zan,fenge;
        TextView title,jianjie;
        public Holder(@NonNull View itemView) {
            super(itemView);
            fenge = itemView.findViewById(R.id.image_fenge2);
            big_img = itemView.findViewById(R.id.image_list2);
            zan = itemView.findViewById(R.id.image_zan2);
            title = itemView.findViewById(R.id.text_list_name2);
            jianjie = itemView.findViewById(R.id.text_list_jianjie2);
        }
    }
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
