package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.FindAllMovieCommentBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/17 10:57
 * @Description：描述信息
 */
public class CommentAdapter extends XRecyclerView.Adapter<CommentAdapter.Holder> implements ContractInterface.VDYDZ {
    List<FindAllMovieCommentBean.ResultBean> list;
    Context context;
    private int s;
    private int greatNum;
    ContractInterface.PDYDZ pdydz;

    public CommentAdapter(List<FindAllMovieCommentBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_layout, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.pingImg.setImageURI(list.get(i).getCommentHeadPic());
        holder.pingName.setText(list.get(i).getCommentUserName());
        pdydz=new MyPresenter(this);
        //设置毫秒值
        long time1 = list.get(i).getCommentTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(time1));
        holder.pingTime.setText(time+"");
        holder.pingPingSum.setText(list.get(i).getReplyNum()+"");
        holder.pingCount.setText(list.get(i).getCommentContent());

        holder.pingPingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.pingZanSum.setText(greatNum+"");
        holder.pingZanSum.setText(list.get(i).getGreatNum()+"");
        int isGreat = list.get(i).getIsGreat();
        if(isGreat == 1){
            Glide.with(context).load(R.drawable.yz).into(holder.pingZanImg);
        }else{
            Glide.with(context).load(R.drawable.pz).into(holder.pingZanImg);
        }
        holder.pingZanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isGreat = list.get(i).getIsGreat();
                if(isGreat == 0){
                    greatNum = list.get(i).getGreatNum();
                    Glide.with(context).load(R.drawable.yz).into(holder.pingZanImg);
                    list.get(i).setIsGreat(1);
                    greatNum++;
                    holder.pingZanSum.setText(greatNum+"");
                    list.get(i).setGreatNum(greatNum);
                    pdydz.PDYDZ(list.get(i).getCommentId());

                }else {
                    greatNum = list.get(i).getGreatNum();
                    Glide.with(context).load(R.drawable.yz).into(holder.pingZanImg);
                    list.get(i).setIsGreat(1);
                    holder.pingZanSum.setText(greatNum+"");
                    pdydz.PDYDZ(list.get(i).getCommentId());
                }
            }
        });

        //点击评论
        holder.pingPingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void VDYDZ(String str) {
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
        Log.e("a123", "VDYDZ: "+str.toString() );
    }

    public class Holder extends XRecyclerView.ViewHolder {
        SimpleDraweeView pingImg;
        TextView pingTime;
        TextView pingPingSum;
        TextView pingZanSum;
        TextView pingName;
        TextView pingCount;
        ImageView pingPingImg;
        ImageView pingZanImg;

        public Holder(@NonNull View itemView) {
            super(itemView);
            pingImg = itemView.findViewById(R.id.ping_img);
            pingPingImg = itemView.findViewById(R.id.ping_ping_img);
            pingZanImg = itemView.findViewById(R.id.ping_zan_img);
            pingTime = itemView.findViewById(R.id.ping_time);
            pingName = itemView.findViewById(R.id.ping_name);
            pingCount = itemView.findViewById(R.id.ping_count);
            pingPingSum = itemView.findViewById(R.id.ping_ping_sum);
            pingZanSum = itemView.findViewById(R.id.ping_zan_sum);

        }
    }
}
