package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.DetailsBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/16 20:54
 * @Description：描述信息
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {
    List<DetailsBean.ResultBean.ShortFilmListBean> list;
    Context context;

    public VideoAdapter(List<DetailsBean.ResultBean.ShortFilmListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.vedio_layout,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

            if (list.get(i).getVideoUrl() != null){
                boolean setUp = holder.standard.setUp(list.get(i).getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                if (setUp) {
                    holder.standard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context).load(list.get(i).getVideoUrl()).into(holder.standard.thumbImageView);
                }
            }

        Log.i("list_bo", "onBindViewHolder: " + list.size());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        public JCVideoPlayerStandard standard;
        public Holder(@NonNull View itemView) {
            super(itemView);
            standard = itemView.findViewById(R.id.video_view1);
        }
    }

}
