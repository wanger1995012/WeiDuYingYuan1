package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/20 17:29
 * @Description：描述信息
 */
public class CinemaAdapter extends XRecyclerView.Adapter<CinemaAdapter.Holder> {
    List<CinemaBean.ResultBean> list;
    Context context;
    MyCallBack myCallBack;

    public CinemaAdapter(List<CinemaBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_layout, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.yuanImg.setImageURI(list.get(i).getLogo());
        holder.yuanJuli.setText(list.get(i).getDistance()+"km");
        holder.yuanAddress.setText(list.get(i).getAddress());
        holder.yuanName.setText(list.get(i).getName());
        int cinema = list.get(i).getFollowCinema();
        if(cinema == 0){
            Glide.with(context).load(R.drawable.zanb).into(holder.img);
        }else if(cinema == 1){
            Glide.with(context).load(R.drawable.zanh).into(holder.img);
        }
        final int id = list.get(i).getId();
        final String address = list.get(i).getAddress();
        final String y_name = list.get(i).getName();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.clickListener(holder.itemView,address,y_name,id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends XRecyclerView.ViewHolder {
        SimpleDraweeView yuanImg;
        TextView yuanName;
        TextView yuanAddress;
        TextView yuanJuli;
        ImageView img;
        public Holder(@NonNull View itemView) {
            super(itemView);
            yuanImg = itemView.findViewById(R.id.yuan_img);
            yuanName = itemView.findViewById(R.id.yuan_name);
            yuanAddress = itemView.findViewById(R.id.yuan_address);
            yuanJuli = itemView.findViewById(R.id.yuan_juli);
            img = itemView.findViewById(R.id.yuan_guanzhu);
        }
    }


    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface MyCallBack{
        public void clickListener(View view, String address, String name, int id);
    }

}
