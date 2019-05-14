package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/14 10:09
 * @Description：描述信息
 */
public class ShowListAdapter3 extends RecyclerView.Adapter<ShowListAdapter3.Holder> {
    List<NowPlayingBean.ResultBean> list;
    Context context;

    public ShowListAdapter3(List<NowPlayingBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_zhan3,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.big_img.setImageURI(list.get(i).getImageUrl());
        holder.title.setText(list.get(i).getName());
        holder.jianjie.setText(list.get(i).getSummary());
        final int status = list.get(i).getFollowMovie();
        Glide.with(context).load(R.drawable.fenge).into(holder.fenge);
        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status == 2){
                    list.get(i).setFollowMovie(1);
                }else{
                    list.get(i).setFollowMovie(2);
                }
            }
        });
        if(status == 2){
            Glide.with(context).load(R.drawable.zanh).into(holder.zan);
        }else{
            Glide.with(context).load(R.drawable.zanb).into(holder.zan);
        }
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
            fenge = itemView.findViewById(R.id.image_fenge3);
            big_img = itemView.findViewById(R.id.image_list3);
            zan = itemView.findViewById(R.id.image_zan3);
            title = itemView.findViewById(R.id.text_list_name3);
            jianjie = itemView.findViewById(R.id.text_list_jianjie3);
        }
    }
}
