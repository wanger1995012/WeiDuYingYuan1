package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bw.movie.R;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.hotactivity.DetailsActivity;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/13 10:47
 * @Description：描述信息
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.Holder>  {
    List<DetailsBean.ResultBean> list;
    List<DetailsBean.ResultBean.ShortFilmListBean> list_bo;
    List<FindAllMovieCommentBean.ResultBean> list_comment;
    Context context;
    DetailsActivity activity;
    ContractInterface.PresenterInterface p = new MyPresenter<>(this);
    TextView xiang_movie_name;
    public DetailsAdapter(List<DetailsBean.ResultBean> list,List<DetailsBean.ResultBean.ShortFilmListBean> list_bo,List<FindAllMovieCommentBean.ResultBean> list_comment, Context context) {
        this.list = list;
        this.context = context;
        activity = (DetailsActivity) context;
        this.list_bo = list_bo;
        this.list_comment = list_comment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.by_id_layout, null);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {


//--------------------------------------------------------------------------------//
//        activity.clickPing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.layout_input.setVisibility(View.VISIBLE);
//            }
//        });
//        activity.input_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = activity.input_count.getText().toString();
//                p.toModelSendCounts(list.get(i).getId(), s);
//                activity.layout_input.setVisibility(View.GONE);
//            }
//        });
//--------------------------------------------------------------------------------//


//        p.toModelFindAllMovieComment(list.get(i).getId(), 1,10);
        activity.view.setImageURI(list.get(i).getImageUrl());
        activity.view.setAlpha(50);

        //剧照
        activity.ju_imageLeftJuZhao1.setImageURI(list.get(i).getPosterList().get(0));
        activity.ju_imageLeftJuZhao2.setImageURI(list.get(i).getPosterList().get(1));
        activity.ju_imageLeftJuZhao3.setImageURI(list.get(i).getPosterList().get(2));
        activity.ju_imageRightJuZhao1.setImageURI(list.get(i).getPosterList().get(3));
        activity.ju_imageRightJuZhao2.setImageURI(list.get(i).getPosterList().get(4));
        activity.ju_imageRightJuZhao3.setImageURI(list.get(i).getPosterList().get(5));
        activity.ju_textMovieName3.setText(list.get(i).getName());
        //半透明背景图
        holder.simpleDraweeView2.setAlpha(50);
        holder.name.setText(list.get(i).getName());
        activity.movie_name1.setText(list.get(i).getName());
        activity.movie_name2.setText(list.get(i).getName());
        //点赞暂时有BUG
        int movie = list.get(i).getFollowMovie();
        if(movie == 1){
            Glide.with(context).load(R.drawable.zanb).into(holder.image2_dianzan);
        }else if(movie == 2){
            Glide.with(context).load(R.drawable.zanh).into(holder.image2_dianzan);
        }
        holder.image2_dianzan.setOnClickListener(new View.OnClickListener() {
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
        Glide.with(context).load(list.get(i).getImageUrl()).into(holder.img);
        holder.image2_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
//        MinAdapter adapters = new MinAdapter();
//        activity.recyclerViewXiang.setAdapter(adapters);
        //点击详情展示
        holder.text2_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*View view1 = LayoutInflater.from(context).inflate(R.layout.xiangqing_layout,null);
                xiang_movie_name = view1.findViewById(R.id.xiang_movie_name);
                BottomSheetDialog dialog = new BottomSheetDialog(context);
                dialog.setContentView(R.layout.xiangqing_layout);
                xiang_movie_name.setText(list.get(i).getName());
                dialog.show();*/

                activity.simpleDrawView.setImageURI(list.get(i).getImageUrl());
                activity.simpleDrawView.setAlpha(50);
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
        //点击预告展示
        holder.text2_yugao.setOnClickListener(new View.OnClickListener() {

            public VideoAdapter adapter;

            @Override
            public void onClick(View view) {

                activity.spdv.setImageURI(list.get(i).getImageUrl());
                activity.spdv.setAlpha(50);
                activity.spdv.setVisibility(View.VISIBLE);
                activity.recyclerView.setVisibility(View.GONE);
                activity.layout_xianshi.setVisibility(View.VISIBLE);
                adapter = new VideoAdapter(list.get(i).getShortFilmList(), context);
                LinearLayoutManager manager  = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                activity.rec.setVisibility(View.VISIBLE);
                activity.rec.setLayoutManager(manager);
                activity.rec.setAdapter(adapter);

                activity.vedioYincang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.layout_xianshi.setVisibility(View.GONE);
                        activity.recyclerView.setVisibility(View.VISIBLE);
                        activity.spdv.setVisibility(View.GONE);
                        activity.rec.setVisibility(View.GONE);
                    }
                });
            }
        });

        //点击剧照

        holder.text2_juzhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.ju_simpleDrawView3.setImageURI(list.get(i).getImageUrl());
                activity.ju_simpleDrawView3.setAlpha(50);
                activity.ju_simpleDrawView3.setVisibility(View.VISIBLE);
                activity.recyclerView.setVisibility(View.GONE);
                activity.ju_layoutXianshiJuzhao.setVisibility(View.VISIBLE);

                Log.i("image", "image0: " + list.get(i).getPosterList().get(0));
                Log.i("image", "image1: " + list.get(i).getPosterList().get(1));
                Log.i("image", "image2: " + list.get(i).getPosterList().get(2));
                Log.i("image", "image3: " + list.get(i).getPosterList().get(3));
                Log.i("image", "image4: " + list.get(i).getPosterList().get(4));
                Log.i("image", "image5: " + list.get(i).getPosterList().get(5));
                activity.ju_juzhaoYinCang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.recyclerView.setVisibility(View.VISIBLE);
                        activity.ju_layoutXianshiJuzhao.setVisibility(View.GONE);
                        activity.ju_simpleDrawView3.setVisibility(View.GONE);
                    }
                });
            }
        });


        holder.text2_yingping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                activity.ying_simpleDrawView4.setImageURI(list.get(i).getImageUrl());
                activity.ying_simpleDrawView4.setAlpha(50);
                activity.recyclerView.setVisibility(View.GONE);

                activity.rec_yingping.setVisibility(View.VISIBLE);
                activity.ying_simpleDrawView4.setVisibility(View.VISIBLE);
                activity.ying_yingpingLayout.setVisibility(View.VISIBLE);


                activity.clickPing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.layout_input.setVisibility(View.VISIBLE);
                        activity.ying_yingpingLayout.setVisibility(View.GONE);
                    }
                });
                activity.input_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = activity.input_count.getText().toString();
                        activity.p.toModelSendCounts(list.get(i).getId() , s);
                        activity.layout_input.setVisibility(View.GONE);
                        activity.ying_yingpingLayout.setVisibility(View.VISIBLE);
                        Toast.makeText(context, s,Toast.LENGTH_LONG).show();
                    }
                });

                activity.ying_yingpingYincang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.recyclerView.setVisibility(View.VISIBLE);
                        activity.ying_simpleDrawView4.setVisibility(View.GONE);
                        activity.ying_yingpingLayout.setVisibility(View.GONE);
                        activity.rec_yingping.setVisibility(View.GONE);
                    }
                });
            }
        });

        //购票事件
        holder.text2_goupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //带参跳转
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
        ImageView img,image2_call,image2_dianzan;

        TextView name,text2_goupiao,text2_xiangqing,text2_yugao,text2_juzhao,text2_yingping;
        //JCVideoPlayerStandard jcv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            //jcv = itemView.findViewById(R.id.video_view1);
            image2_dianzan = itemView.findViewById(R.id.image2_dianzan);
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
