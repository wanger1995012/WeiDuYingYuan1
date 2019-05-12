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
import com.bw.movie.wdyy.bean.ComingSoonBean;
import com.bw.movie.wdyy.bean.HotMovieListBean;
import com.bw.movie.wdyy.bean.NowPlayingBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/12 21:17
 * @Description：描述信息
 */
public class MyBigAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<HotMovieListBean> bean1;
    List<NowPlayingBean>   bean2;
    List<ComingSoonBean>   bean3;
    List<Integer> list_banner = new ArrayList<>();
    List<Object> list = new ArrayList<>();
    Context context;

    public MyBigAdapter(List<HotMovieListBean> bean1, List<NowPlayingBean> bean2, List<ComingSoonBean> bean3, Context context) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 1){
            MyViewHolder1 myViewHolder1 = new MyViewHolder1
                    (LayoutInflater.from(context).inflate(R.layout.viewholder1_layout , null));
            return myViewHolder1;
        }else if(i == 2){
            MyViewHolder2 myViewHolder2 = new MyViewHolder2
                    (LayoutInflater.from(context).inflate(R.layout.viewholder1_layout2 , null));
            return myViewHolder2;
        }else if(i == 3){
            MyViewHolder3 myViewHolder3 = new MyViewHolder3
                    (LayoutInflater.from(context).inflate(R.layout.viewholder1_layout3 , null));
            return myViewHolder3;
        }else if(i == 4){
            MyViewHolder4 myViewHolder4 = new MyViewHolder4
                    (LayoutInflater.from(context).inflate(R.layout.viewholder1_layout4 , null));
            return myViewHolder4;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof MyViewHolder1){
            ((MyViewHolder1) viewHolder).banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).setImages(list_banner).setDelayTime(2000).isAutoPlay(true).start();
        }else if(viewHolder instanceof MyViewHolder2){
            Glide.with(context).load(bean1.get(i).getResult().get(0).getImageUrl()).into(((MyViewHolder2) viewHolder).img);
        }
    }

    @Override
    public int getItemCount() {
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }else if(position == 1){
            return 2;
        }else if(position == 2){
            return 3;
        }else if(position == 3){
            return 4;
        }else{
            return 5;
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{

        Banner banner;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner_my);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder{
        ImageView img;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_view1);
        }
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder{

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MyViewHolder4 extends RecyclerView.ViewHolder{

        public MyViewHolder4(@NonNull View itemView) {
            super(itemView);
        }
    }

}
