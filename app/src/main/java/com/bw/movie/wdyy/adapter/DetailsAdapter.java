package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.DetailsBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.hotactivity.DetailsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/13 10:47
 * @Description：描述信息
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.Holder> {
    List<DetailsBean.ResultBean> list;
    Context context;
    DetailsActivity activity;

    public DetailsAdapter(List<DetailsBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
        activity = (DetailsActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.by_id_layout, null);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.simpleDraweeView2.setImageURI(list.get(i).getImageUrl());
        holder.simpleDraweeView2.setAlpha(50);
        holder.name.setText(list.get(i).getName());

        Glide.with(context).load(list.get(i).getImageUrl()).into(holder.img);
        holder.image2_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
//        MinAdapter adapters = new MinAdapter();
//        activity.recyclerViewXiang.setAdapter(adapters);
        holder.text2_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.recyclerView.setVisibility(View.GONE);
                activity.simpleDrawView.setVisibility(View.VISIBLE);
                activity.simpleDrawView.setImageURI(list.get(i).getImageUrl());
                activity.simpleDrawView.setAlpha(50);
                activity.xiangType.setText("类型："+list.get(i).getMovieTypes());
                activity.xiangAddress.setText("产地："+list.get(i).getPlaceOrigin());
                activity.xiangDaoyan.setText("导演："+ list.get(i).getDirector());
                activity.xiangTime.setText("时长："+list.get(i).getDuration());
                activity.xiangXiang.setText(list.get(i).getSummary());
                activity.layoutXianshi.setVisibility(View.VISIBLE);
                activity.xiangMovieName.setText(list.get(i).getName());
                Glide.with(view).load(list.get(i).getImageUrl()).into(activity.xiangMovieImage);
                activity.xiangYincang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.layoutXianshi.setVisibility(View.GONE);
                        activity.simpleDrawView.setVisibility(View.GONE);
                        activity.recyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        SimpleDraweeView simpleDraweeView2;
        ImageView img,image2_call;
        TextView name,text2_goupiao,text2_xiangqing,text2_yugao,text2_juzhao,text2_yingping;

        public Holder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relative_touming);
            simpleDraweeView2 = itemView.findViewById(R.id.simple_draw_movie);
            img = itemView.findViewById(R.id.image2_big_img);
            image2_call = itemView.findViewById(R.id.image2_call);
            name = itemView.findViewById(R.id.text2_name);
            text2_goupiao = itemView.findViewById(R.id.text2_goupiao);
            text2_xiangqing = itemView.findViewById(R.id.text2_xiangqing);
            text2_yugao = itemView.findViewById(R.id.text2_yugao);
            text2_juzhao = itemView.findViewById(R.id.text2_juzhao);
            text2_yingping = itemView.findViewById(R.id.text2_yingping);
        }
    }

}
